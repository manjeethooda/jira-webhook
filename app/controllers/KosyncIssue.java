package controllers;

import java.util.ArrayList;
import java.util.List;

import models.kosyncIssue;
import models.restapi.HTTPResponse;
import models.restapi.HTTPStatus;
import models.restapi.HTTPStatusCode;
import models.restapi.Metadata;
import models.restapi.MetadataGetCollection;
import models.restapi.MetadataPostUser;
import models.restapi.UserDeleteForm;
import models.restapi.UserGetForm;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.StopWatch;
import org.mongodb.morphia.query.Query;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;

import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.IssueDAO;
import utils.DAOUtils;
import play.Logger;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Transition;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.internal.ServerVersionConstants;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.google.common.collect.Lists;
import org.codehaus.jettison.json.JSONException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List; 
import java.net.URI;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;



public class KosyncIssue extends Controller {
	static IssueDAO issueDAO = DAOUtils.issueDAO;
	static URI jiraServerUri = URI.create("https://testingjirawebhook.atlassian.net");	
	
	static String id = "900000";
	public static Result getIssueById() {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();		
		Issue issue = null;					// jira issue 
		kosyncIssue kIssue = null; 				// kosync issue for getting URL to fetch jira issue 
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if (isInvalidIssueId(id)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("issue id invalid. Make sure id is not empty or null. Also check if its a valid UUID");
		} else if (issueDAO == null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to issue DB");
		} else {
			try {
				kIssue = issueDAO.getByJiraId(id);
				/*if (kIssue == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("Issue not found in DB");
				} else*/ {
					httpStatus.setCode(HTTPStatusCode.OK);
					httpStatus.setDeveloperMessage("Issue found in DB");
					
					final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
					final JiraRestClient restClient = 
								factory.createWithBasicHttpAuthentication(jiraServerUri, "user", "password");
					
					try {
						final int buildNumber = restClient.getMetadataClient().getServerInfo().claim().getBuildNumber();

						// first let's get and print all visible projects (only jira4.3+)
						/*if (buildNumber >= ServerVersionConstants.BN_JIRA_4_3) {
							final Iterable<BasicProject> allProjects = 
										restClient.getProjectClient().getAllProjects().claim();
							for (BasicProject project : allProjects) {
								System.out.println(project);
							}
						}*/

						// let's now print all issues matching a JQL string (here: all assigned issues)
						/*if (buildNumber >= ServerVersionConstants.BN_JIRA_4_3) {
							final SearchResult searchResult = 
									restClient.getSearchClient().searchJql("assignee is not EMPTY").claim();
							for (BasicIssue basicIssue : searchResult.getIssues()) {
								System.out.println(basicIssue.getKey());
							}
						}*/

						issue = restClient.getIssueClient().getIssue("MANJ-26").claim();
						System.out.println(issue);
					} finally {
						restClient.close();
					}
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
				httpStatus.setDeveloperMessage("Issue not found. \n"
								+ "Either id is invalid or issue doesnot exist in database. \n"
								+ "Also check that api is pointed to correct database. \n"
								+ "If all seems ok, notify the fucking developers.");
				debugInfo = ExceptionUtils.getFullStackTrace(e
						.fillInStackTrace());
				e.printStackTrace();
			}
		}

		// 4. Stop stopwatch
		stopWatch.stop();

		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		HTTPResponse<Issue, Metadata, String> httpResponse = new HTTPResponse<Issue, Metadata, String>(
				httpStatus, metadata, issue, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	/**
	 * @param id
	 * @return
	 */
	private static boolean isInvalidIssueId(String id) {
		return id.isEmpty() || id == null;
	}
}
