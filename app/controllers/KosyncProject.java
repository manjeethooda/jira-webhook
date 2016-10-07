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
import jsonModels.*;

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
	

	//post project
	public static Result postProjects() {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		JsonProject jsonProject = null;
		kosyncProject kProject = new kosyncProject();
		MetadataPostUser metadata = new MetadataPostUser();
		String debugInfo = null;

		// 3. Calculate response
		Form<JsonProject> form;
		try {
			System.out.println("kuch bhi yahan tak pahunch");
			form = Form.form(JsonProject.class);
			System.out.println("printing form");
			System.out.println(form);
			if (form.hasErrors()) {
				throw new Exception("Form has errors");
			}
			jsonProject = form.bindFromRequest().get();
			if (projectDAO == null) {
				// if not connected to Users DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to Project DB");
			} else {
				if (projectDAO.getByJiraId(jsonProject.project.getKey().toString()) != null) {
					if (projectDAO.getByJiraURL(jsonProject.project.getSelf().toString()) != null) {
						metadata.setNewRecord(false);
						httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
						httpStatus.setDeveloperMessage("Project with same URL and key already exists");
					}
				} else {
					System.out.println("writing to kProject");
					kProject.setJiraKey(jsonProject.project.getKey());
					kProject.setjiraURL(jsonProject.getJiraURL());
					kProject.setJiraUser(jsonProject.getJiraUser());
					kProject.setJiraAuthKey(jsonProject.getJiraAuth());
					System.out.println("written to kProject");
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
			}
		} catch (Exception e1) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("Error in submitted query!! Check models.Users.java file");
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

	public static Result getProject(String key) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
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

	/**
	 * @param id
	 * @return
	 */
	private static boolean isInvalidProjectId(String id) {
		return id.isEmpty() || id == null;
	}
}
