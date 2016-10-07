package controllers;

import java.util.ArrayList;
import java.util.List;

import models.kosyncProject;
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
import play.Logger;
import play.Play;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProjectDAO;
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
import com.atlassian.jira.rest.client.api.domain.Project;
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

public class KosyncProject extends Controller {
	static ProjectDAO projectDAO = DAOUtils.projectDAO;
	static String jiraUser = Play.application().configuration().getString("jiraUser");
	static String jiraAuth = Play.application().configuration().getString("jiraAuth");
	static URI jiraServerUri = URI.create(Play.application().configuration().getString("jiraUri"));

	public static Result getProject(String key) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		Project project = null;
		kosyncProject kProject = new kosyncProject();
		MetadataPostUser metadata = new MetadataPostUser();
		String debugInfo = null;

		// 3. Calculate response
		try {
			if (projectDAO == null) {
				// if not connected to Users DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to Project DB");
			} else {
				try {
					final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
					final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, jiraUser,
							jiraAuth);

					try {
						final int buildNumber = restClient.getMetadataClient().getServerInfo().claim().getBuildNumber();

						// first let's get and print all visible projects (only
						// jira4.3+)
						/*
						 * if (buildNumber >=
						 * ServerVersionConstants.BN_JIRA_4_3) { final
						 * Iterable<BasicProject> allProjects =
						 * restClient.getProjectClient().getAllProjects().claim(
						 * ); for (BasicProject project : allProjects) {
						 * System.out.println(project); } }
						 */

						project = restClient.getProjectClient().getProject(key).claim();
						System.out.println(project);
					} finally {
						restClient.close();
					}

					if (projectDAO.getByJiraId(project.getId().toString()) != null) {
						metadata.setNewRecord(false);
						httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
						httpStatus.setDeveloperMessage("Project with same jira ID already exists");
					} else if (projectDAO.get(project.getSelf().toString()) != null) {
						metadata.setNewRecord(false);
						httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
						httpStatus.setDeveloperMessage("Project with same URL already exists");
					} else {
						kProject.setJiraId(project.getId().toString());
						kProject.setjiraURL(project.getSelf().toString());

						// @ TODO :
						kProject.setJiraUser(jiraUser);
						kProject.setJiraAuth(jiraAuth);

						projectDAO.save(kProject, WriteConcern.SAFE);
						kProject = projectDAO.get(kProject.get_id());
						if (kProject == null) {
							httpStatus.setCode(HTTPStatusCode.GONE);
							httpStatus
									.setDeveloperMessage("Project was written to DB but was not returned successfully");
						} else {
							httpStatus.setCode(HTTPStatusCode.RESOURCE_CREATED);
							httpStatus.setDeveloperMessage("Project was successfully written to DB");
						}
					}
				} catch (Exception e) {
					kProject = null;
					httpStatus.setCode(HTTPStatusCode.INTERNAL_SERVER_ERROR);
					httpStatus.setDeveloperMessage("Exception occured while writing to Project DB");
					debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("Error in submitted query!! Check models.kosyncProject.java file");
			debugInfo = ExceptionUtils.getFullStackTrace(e1.fillInStackTrace());
			e1.printStackTrace();
		}

		// 4. Stop stopwatch
		stopWatch.stop();
		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		HTTPResponse<kosyncProject, MetadataPostUser, String> httpResponse = new HTTPResponse<kosyncProject, MetadataPostUser, String>(
				httpStatus, metadata, kProject, debugInfo);
		System.out.println(kProject);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	public static Result putProject(String key) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		Project project = null;
		kosyncProject kProject = new kosyncProject();
		MetadataPostUser metadata = new MetadataPostUser();
		String debugInfo = null;

		// 3. Calculate response
		try {
			if (projectDAO == null) {
				// if not connected to Users DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to Project DB");
			} else {
				try {
					final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
					final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, jiraUser,
							jiraAuth);

					try {
						final int buildNumber = restClient.getMetadataClient().getServerInfo().claim().getBuildNumber();

						// first let's get and print all visible projects (only
						// jira4.3+)
						/*
						 * if (buildNumber >=
						 * ServerVersionConstants.BN_JIRA_4_3) { final
						 * Iterable<BasicProject> allProjects =
						 * restClient.getProjectClient().getAllProjects().claim(
						 * ); for (BasicProject project : allProjects) {
						 * System.out.println(project); } }
						 */

						project = restClient.getProjectClient().getProject(key).claim();
						System.out.println(project);
					} finally {
						restClient.close();
					}

					if (projectDAO.getByJiraId(project.getId().toString()) != null) {
						metadata.setNewRecord(false);
						httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
						httpStatus.setDeveloperMessage("Project with same jira ID already exists");
					} else if (projectDAO.get(project.getSelf().toString()) != null) {
						metadata.setNewRecord(false);
						httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
						httpStatus.setDeveloperMessage("Project with same URL already exists");
					} else {
						kProject.setJiraId(project.getId().toString());
						kProject.setjiraURL(project.getSelf().toString());

						// @ TODO :
						kProject.setJiraUser(jiraUser);
						kProject.setJiraAuth(jiraAuth);

						projectDAO.save(kProject, WriteConcern.SAFE);
						kProject = projectDAO.get(kProject.get_id());
						if (kProject == null) {
							httpStatus.setCode(HTTPStatusCode.GONE);
							httpStatus
									.setDeveloperMessage("Project was written to DB but was not returned successfully");
						} else {
							httpStatus.setCode(HTTPStatusCode.RESOURCE_CREATED);
							httpStatus.setDeveloperMessage("Project was successfully written to DB");
						}
					}
				} catch (Exception e) {
					kProject = null;
					httpStatus.setCode(HTTPStatusCode.INTERNAL_SERVER_ERROR);
					httpStatus.setDeveloperMessage("Exception occured while writing to Project DB");
					debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("Error in submitted query!! Check models.kosyncProject.java file");
			debugInfo = ExceptionUtils.getFullStackTrace(e1.fillInStackTrace());
			e1.printStackTrace();
		}

		// 4. Stop stopwatch
		stopWatch.stop();
		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		HTTPResponse<kosyncProject, MetadataPostUser, String> httpResponse = new HTTPResponse<kosyncProject, MetadataPostUser, String>(
				httpStatus, metadata, kProject, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	/**
	 * @param id
	 * @return
	 */
	private static boolean isInvalidProjectId(String id) {
		return id.isEmpty() || id == null;
	}
}
