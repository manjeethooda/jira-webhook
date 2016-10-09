package controllers;

import java.util.ArrayList;
import java.util.List;

import models.KosyncIssue;
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



public class KosyncIssues extends Controller {
	static IssueDAO issueDAO = DAOUtils.issueDAO;
	
	public static Result getIssueById(String id) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncIssue kosyncIssue = null;
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if (isInvalidIssueId(id)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus
			.setDeveloperMessage("KosyncIssue id invalid. Make sure id is not empty or null. Also check if its a valid UUID");
		} else if (issueDAO == null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to KosyncIssue DB");
		} else {
			try {
				kosyncIssue = issueDAO.get(id);
				if (kosyncIssue == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("KosyncIssue not found in DB");
				} else {
					httpStatus.setCode(HTTPStatusCode.OK);
					httpStatus.setDeveloperMessage("KosyncIssue found in DB");
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
				httpStatus
						.setDeveloperMessage("KosyncIssue not found. \n"
								+ "Either id is invalid or kosyncIssue doesnot exist in database. \n"
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
		HTTPResponse<KosyncIssue, Metadata, String> httpResponse = new HTTPResponse<KosyncIssue, Metadata, String>(
				httpStatus, metadata, kosyncIssue, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	public static Result getIssueByParent(String parentIssue){
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncIssue kosyncIssue = null;
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if (isInvalidIssueId(parentIssue)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus
			.setDeveloperMessage("KosyncIssue Key invalid. Make sure Key is not empty or null. Also check if its a valid Key");
		} else if (issueDAO == null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to KosyncIssue DB");
		} else {
			try {
				kosyncIssue = issueDAO.getByParent(parentIssue);
				if (kosyncIssue == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("KosyncIssue not found in DB");
				} else {
					httpStatus.setCode(HTTPStatusCode.OK);
					httpStatus.setDeveloperMessage("KosyncIssue found in DB");
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
				httpStatus
						.setDeveloperMessage("KosyncIssue not found. \n"
								+ "Either id is invalid or kosyncIssue doesnot exist in database. \n"
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
		HTTPResponse<KosyncIssue, Metadata, String> httpResponse = new HTTPResponse<KosyncIssue, Metadata, String>(
				httpStatus, metadata, kosyncIssue, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	public static Result getIssueByChild(String childIssue){
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncIssue kosyncIssue = null;
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if (isInvalidIssueId(childIssue)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus
			.setDeveloperMessage("KosyncIssue Key invalid. Make sure Key is not empty or null. Also check if its a valid Key");
		} else if (issueDAO == null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to KosyncIssue DB");
		} else {
			try {
				kosyncIssue = issueDAO.getByChild(childIssue);
				if (kosyncIssue == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("KosyncIssue not found in DB");
				} else {
					httpStatus.setCode(HTTPStatusCode.OK);
					httpStatus.setDeveloperMessage("KosyncIssue found in DB");
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
				httpStatus
						.setDeveloperMessage("KosyncIssue not found. \n"
								+ "Either id is invalid or kosyncIssue doesnot exist in database. \n"
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
		HTTPResponse<KosyncIssue, Metadata, String> httpResponse = new HTTPResponse<KosyncIssue, Metadata, String>(
				httpStatus, metadata, kosyncIssue, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	public static Result getIssuesByKey(String issueKey) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		MetadataGetCollection metadata = new MetadataGetCollection();
		List<KosyncIssue> kosyncIssues = new ArrayList<KosyncIssue>();
		String debugInfo = null;

		// 3. Calculate response
		DBObject dbObjQuery = null;
		UserGetForm issueGetForm = null;
		Integer numFound = 0;

		try {
			Form<UserGetForm> form = Form.form(UserGetForm.class);
			if(form.hasErrors()) {
				throw new Exception("Form has errors");
			}
			issueGetForm = form.bindFromRequest().get();

			if(issueDAO==null) {
				// if not connected to KosyncIssues DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to KosyncIssue DB");
			} else {
			    if(issueGetForm.action != null && issueGetForm.action.equalsIgnoreCase("deleteall")) {
			        issueDAO.getDatastore().delete(issueDAO.getDatastore().createQuery(KosyncIssue.class).filter("isActive", true));
                    httpStatus.setCode(HTTPStatusCode.OK);
                    httpStatus.setDeveloperMessage("KosyncIssues were successfully deleted from DB");
			    } else
				if (issueGetForm.q == null || issueGetForm.q == "") {
					httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
					httpStatus.setDeveloperMessage("Request Query invalid. Make sure query is not empty or null.");
				} else if (issueGetForm.method.equals("basic")) {
					// do nothing as of now
				} else if (issueGetForm.method.equals("generic")) {
					try {
					    dbObjQuery = (DBObject) JSON.parse(issueGetForm.q);
						DBObject dbObjSortQuery = (DBObject) JSON.parse(issueGetForm.sort);
						List<DBObject> issueDBObject = issueDAO.getCollection().find(dbObjQuery).sort(dbObjSortQuery).skip(issueGetForm.start).limit(issueGetForm.rows).toArray();
						numFound = issueDAO.getCollection().find(dbObjQuery).count();
						kosyncIssues = new ObjectMapper().readValue(issueDBObject.toString(), new TypeReference<List<KosyncIssue>>() {});
						httpStatus.setCode(HTTPStatusCode.OK);
						httpStatus.setDeveloperMessage("Query executed successfully.");
					} catch (Exception e) {
						httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
						httpStatus.setDeveloperMessage("KosyncIssue not found. "
								+ "Either id is invalid or kosyncIssue doesnot exist in database. "
								+ "Also check that api is pointed to correct database. "
								+ "If all seems ok, notify the fucking developers. "
								+ e.toString());
						debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
						e.printStackTrace();
					}
				} else {
					httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
					httpStatus.setDeveloperMessage("Request method invalid. Make sure method parameter is passed correctly.");
				}
			}
		} catch (Exception e) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("Error in submitted query!! Check models.UserGetForm.java file");
			debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
			e.printStackTrace();
		}

		// 4. Stop stopwatch
		stopWatch.stop();

		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		metadata.setNumFound(numFound);
		HTTPResponse<List<KosyncIssue>, MetadataGetCollection, String> httpResponse = new HTTPResponse<List<KosyncIssue>, MetadataGetCollection, String>(httpStatus, metadata, kosyncIssues, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));

	}

	public static Result getIssuesByProjectId(String projectId) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		MetadataGetCollection metadata = new MetadataGetCollection();
		List<KosyncIssue> kosyncIssues = new ArrayList<KosyncIssue>();
		String debugInfo = null;

		// 3. Calculate response
		DBObject dbObjQuery = null;
		UserGetForm issueGetForm = null;
		Integer numFound = 0;

		try {
			Form<UserGetForm> form = Form.form(UserGetForm.class);
			if(form.hasErrors()) {
				throw new Exception("Form has errors");
			}
			issueGetForm = form.bindFromRequest().get();

			if(issueDAO==null) {
				// if not connected to KosyncIssues DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to KosyncIssue DB");
			} else {
			    if(issueGetForm.action != null && issueGetForm.action.equalsIgnoreCase("deleteall")) {
			        issueDAO.getDatastore().delete(issueDAO.getDatastore().createQuery(KosyncIssue.class).filter("isActive", true));
                    httpStatus.setCode(HTTPStatusCode.OK);
                    httpStatus.setDeveloperMessage("KosyncIssues were successfully deleted from DB");
			    } else
				if (issueGetForm.q == null || issueGetForm.q == "") {
					httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
					httpStatus.setDeveloperMessage("Request Query invalid. Make sure query is not empty or null.");
				} else if (issueGetForm.method.equals("basic")) {
					// do nothing as of now
				} else if (issueGetForm.method.equals("generic")) {
					try {
					    dbObjQuery = (DBObject) JSON.parse(issueGetForm.q);
						DBObject dbObjSortQuery = (DBObject) JSON.parse(issueGetForm.sort);
						List<DBObject> issueDBObject = issueDAO.getCollection().find(dbObjQuery).sort(dbObjSortQuery).skip(issueGetForm.start).limit(issueGetForm.rows).toArray();
						numFound = issueDAO.getCollection().find(dbObjQuery).count();
						kosyncIssues = new ObjectMapper().readValue(issueDBObject.toString(), new TypeReference<List<KosyncIssue>>() {});
						httpStatus.setCode(HTTPStatusCode.OK);
						httpStatus.setDeveloperMessage("Query executed successfully.");
					} catch (Exception e) {
						httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
						httpStatus.setDeveloperMessage("KosyncIssue not found. "
								+ "Either id is invalid or kosyncIssue doesnot exist in database. "
								+ "Also check that api is pointed to correct database. "
								+ "If all seems ok, notify the fucking developers. "
								+ e.toString());
						debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
						e.printStackTrace();
					}
				} else {
					httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
					httpStatus.setDeveloperMessage("Request method invalid. Make sure method parameter is passed correctly.");
				}
			}
		} catch (Exception e) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("Error in submitted query!! Check models.UserGetForm.java file");
			debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
			e.printStackTrace();
		}

		// 4. Stop stopwatch
		stopWatch.stop();

		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		metadata.setNumFound(numFound);
		HTTPResponse<List<KosyncIssue>, MetadataGetCollection, String> httpResponse = new HTTPResponse<List<KosyncIssue>, MetadataGetCollection, String>(httpStatus, metadata, kosyncIssues, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));

	}
	
	
	public static Result deleteIssueById(String id) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncIssue kosyncIssue = null;
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if(isInvalidIssueId(id)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("KosyncIssue id invalid. Make sure id is not empty or null. Also check if its a valid UUID");
		} else if(issueDAO==null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to KosyncIssue DB");
		} else {
			try {
				kosyncIssue = issueDAO.get(id);
				if(kosyncIssue == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("KosyncIssue not found in DB");
				} else {
					issueDAO.delete(kosyncIssue, WriteConcern.SAFE);
					kosyncIssue = issueDAO.get(kosyncIssue.get_id());
					if(kosyncIssue == null) {
						httpStatus.setCode(HTTPStatusCode.RESOURCE_DELETED);
						httpStatus.setDeveloperMessage("KosyncIssue was successfully deleted from DB");
					} else {
						httpStatus.setCode(HTTPStatusCode.GONE);
						httpStatus.setDeveloperMessage("KosyncIssue could not be deleted from DB. Report the problem.");
					}
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.INTERNAL_SERVER_ERROR);
				httpStatus.setDeveloperMessage("Could not complete delete action. \n"
						+ "Either id is invalid or kosyncIssue doesnot exist in database. \n"
						+ "Also check that api is pointed to correct database. \n"
						+ "If all seems ok, notify the fucking developers.");
				debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
				e.printStackTrace();
			}
		}

		// 4. Stop stopwatch
		stopWatch.stop();

		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		HTTPResponse<KosyncIssue, Metadata, String> httpResponse = new HTTPResponse<KosyncIssue, Metadata, String>(httpStatus, metadata, kosyncIssue, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}
	
	//post issue
	public static Result postIssues() {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncIssue kosyncIssue = null;
		MetadataPostUser metadata = new MetadataPostUser();
		String debugInfo = null;

		// 3. Calculate response
		Form<KosyncIssue> form;
		try {
			form = Form.form(KosyncIssue.class);
			if (form.hasErrors()) {
				throw new Exception("Form has errors");
			}
			kosyncIssue = form.bindFromRequest().get();
			if (issueDAO == null) {
				// if not connected to KosyncIssues DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to Issue DB");
			} else {
				try {
                    if (issueDAO.getByParent(kosyncIssue.getParentIssue()) != null  ||
			issueDAO.getByProjectId(kosyncIssue.getProjectId()) != null &&
			issueDAO.getByKey(kosyncIssue.getVendorKey()) != null  ){
                        metadata.setNewRecord(false);
                        httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
                        httpStatus.setDeveloperMessage("Issue already exists");
                    } else {
                        issueDAO.save(kosyncIssue, WriteConcern.SAFE);
                        kosyncIssue = issueDAO.get(kosyncIssue.get_id());
                        if (kosyncIssue == null) {
                            httpStatus.setCode(HTTPStatusCode.GONE);
                            httpStatus.setDeveloperMessage("Issue was written to DB but was not returned successfully");
                        } else {
                            httpStatus.setCode(HTTPStatusCode.RESOURCE_CREATED);
                            httpStatus.setDeveloperMessage("Issue was successfully written to DB");
                        }
                    }
				} catch (Exception e) {
					kosyncIssue = null;
					httpStatus.setCode(HTTPStatusCode.INTERNAL_SERVER_ERROR);
					httpStatus
							.setDeveloperMessage("Exception occured while writing to Issue DB");
					debugInfo = ExceptionUtils.getFullStackTrace(e
							.fillInStackTrace());
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("Error in submitted query!! Check models.KosyncIssue.java file");
			debugInfo = ExceptionUtils.getFullStackTrace(e1.fillInStackTrace());
			e1.printStackTrace();
		}

		// 4. Stop stopwatch
		stopWatch.stop();
		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		HTTPResponse<KosyncIssue, MetadataPostUser, String> httpResponse = new HTTPResponse<KosyncIssue, MetadataPostUser, String>(
				httpStatus, metadata, kosyncIssue, debugInfo);
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
