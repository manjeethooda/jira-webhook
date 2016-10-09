package controllers;

import java.util.ArrayList;
import java.util.List;

import models.LinkedAccount;
import models.KosyncProject;
import models.KosyncProject;
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
import services.ProjectDAO;
import utils.DAOUtils;
import play.Logger;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;

public class KosyncProjects extends Controller {
	static ProjectDAO projectDAO = DAOUtils.projectDAO;
	
	public static Result getProjectById(String id) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncProject kosyncProject = null;
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if (isInvalidProjectId(id)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus
			.setDeveloperMessage("KosyncProject id invalid. Make sure id is not empty or null. Also check if its a valid UUID");
		} else if (projectDAO == null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to KosyncProject DB");
		} else {
			try {
				kosyncProject = projectDAO.get(id);
				if (kosyncProject == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("KosyncProject not found in DB");
				} else {
					httpStatus.setCode(HTTPStatusCode.OK);
					httpStatus.setDeveloperMessage("KosyncProject found in DB");
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
				httpStatus
						.setDeveloperMessage("KosyncProject not found. \n"
								+ "Either id is invalid or kosyncProject doesnot exist in database. \n"
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
		HTTPResponse<KosyncProject, Metadata, String> httpResponse = new HTTPResponse<KosyncProject, Metadata, String>(
				httpStatus, metadata, kosyncProject, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	public static Result getProjectByURL(String projectURL){
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncProject kosyncProject = null;
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if (isInvalidProjectId(projectURL)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus
			.setDeveloperMessage("KosyncProject URL invalid. Make sure URL is not empty or null. Also check if its a valid URL");
		} else if (projectDAO == null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to KosyncProject DB");
		} else {
			try {
				kosyncProject = projectDAO.getByProjectURL(projectURL);
				if (kosyncProject == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("KosyncProject not found in DB");
				} else {
					httpStatus.setCode(HTTPStatusCode.OK);
					httpStatus.setDeveloperMessage("KosyncProject found in DB");
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
				httpStatus
						.setDeveloperMessage("KosyncProject not found. \n"
								+ "Either id is invalid or kosyncProject doesnot exist in database. \n"
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
		HTTPResponse<KosyncProject, Metadata, String> httpResponse = new HTTPResponse<KosyncProject, Metadata, String>(
				httpStatus, metadata, kosyncProject, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}

	public static Result getProjectsByKey(String projectKey) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		MetadataGetCollection metadata = new MetadataGetCollection();
		List<KosyncProject> kosyncProjects = new ArrayList<KosyncProject>();
		String debugInfo = null;

		// 3. Calculate response
		DBObject dbObjQuery = null;
		UserGetForm projectGetForm = null;
		Integer numFound = 0;

		try {
			Form<UserGetForm> form = Form.form(UserGetForm.class);
			if(form.hasErrors()) {
				throw new Exception("Form has errors");
			}
			projectGetForm = form.bindFromRequest().get();

			if(projectDAO==null) {
				// if not connected to KosyncProjects DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to KosyncProject DB");
			} else {
			    if(projectGetForm.action != null && projectGetForm.action.equalsIgnoreCase("deleteall")) {
			        projectDAO.getDatastore().delete(projectDAO.getDatastore().createQuery(KosyncProject.class).filter("isActive", true));
                    httpStatus.setCode(HTTPStatusCode.OK);
                    httpStatus.setDeveloperMessage("KosyncProjects were successfully deleted from DB");
			    } else
				if (projectGetForm.q == null || projectGetForm.q == "") {
					httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
					httpStatus.setDeveloperMessage("Request Query invalid. Make sure query is not empty or null.");
				} else if (projectGetForm.method.equals("basic")) {
					// do nothing as of now
				} else if (projectGetForm.method.equals("generic")) {
					try {
					    dbObjQuery = (DBObject) JSON.parse(projectGetForm.q);
						DBObject dbObjSortQuery = (DBObject) JSON.parse(projectGetForm.sort);
						List<DBObject> projectDBObject = projectDAO.getCollection().find(dbObjQuery).sort(dbObjSortQuery).skip(projectGetForm.start).limit(projectGetForm.rows).toArray();
						numFound = projectDAO.getCollection().find(dbObjQuery).count();
						kosyncProjects = new ObjectMapper().readValue(projectDBObject.toString(), new TypeReference<List<KosyncProject>>() {});
						httpStatus.setCode(HTTPStatusCode.OK);
						httpStatus.setDeveloperMessage("Query executed successfully.");
					} catch (Exception e) {
						httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
						httpStatus.setDeveloperMessage("KosyncProject not found. "
								+ "Either id is invalid or kosyncProject doesnot exist in database. "
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
		HTTPResponse<List<KosyncProject>, MetadataGetCollection, String> httpResponse = new HTTPResponse<List<KosyncProject>, MetadataGetCollection, String>(httpStatus, metadata, kosyncProjects, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));

	}
	
	public static Result deleteProjectById(String id) {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncProject kosyncProject = null;
		Metadata metadata = new Metadata();
		String debugInfo = null;

		// 3. Calculate response
		if(isInvalidProjectId(id)) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("KosyncProject id invalid. Make sure id is not empty or null. Also check if its a valid UUID");
		} else if(projectDAO==null) {
			httpStatus.setCode(HTTPStatusCode.GONE);
			httpStatus.setDeveloperMessage("Not connected to KosyncProject DB");
		} else {
			try {
				kosyncProject = projectDAO.get(id);
				if(kosyncProject == null) {
					httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
					httpStatus.setDeveloperMessage("KosyncProject not found in DB");
				} else {
					projectDAO.delete(kosyncProject, WriteConcern.SAFE);
					kosyncProject = projectDAO.get(kosyncProject.get_id());
					if(kosyncProject == null) {
						httpStatus.setCode(HTTPStatusCode.RESOURCE_DELETED);
						httpStatus.setDeveloperMessage("KosyncProject was successfully deleted from DB");
					} else {
						httpStatus.setCode(HTTPStatusCode.GONE);
						httpStatus.setDeveloperMessage("KosyncProject could not be deleted from DB. Report the problem.");
					}
				}
			} catch (Exception e) {
				httpStatus.setCode(HTTPStatusCode.INTERNAL_SERVER_ERROR);
				httpStatus.setDeveloperMessage("Could not complete delete action. \n"
						+ "Either id is invalid or kosyncProject doesnot exist in database. \n"
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
		HTTPResponse<KosyncProject, Metadata, String> httpResponse = new HTTPResponse<KosyncProject, Metadata, String>(httpStatus, metadata, kosyncProject, debugInfo);
		return status(httpStatus.code, Json.toJson(httpResponse));
	}
	
	//post project
	public static Result postProjects() {
		// 1. Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// 2. Initialize http response objects
		HTTPStatus httpStatus = new HTTPStatus();
		KosyncProject kosyncProject = null;
		MetadataPostUser metadata = new MetadataPostUser();
		String debugInfo = null;

		// 3. Calculate response
		Form<KosyncProject> form;
		try {
			form = Form.form(KosyncProject.class);
			if (form.hasErrors()) {
				throw new Exception("Form has errors");
			}
			kosyncProject = form.bindFromRequest().get();
			if (projectDAO == null) {
				// if not connected to KosyncProjects DB
				httpStatus.setCode(HTTPStatusCode.GONE);
				httpStatus.setDeveloperMessage("Not connected to Project DB");
			} else {
				try {
                    if (projectDAO.getByProjectKey(kosyncProject.getProjectKey()) != null &&
			projectDAO.getByProjectURL(kosyncProject.getProjectURL()) != null ){
                        metadata.setNewRecord(false);
                        httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
                        httpStatus.setDeveloperMessage("Project with same name and URL already exists");
                    } else {
                        projectDAO.save(kosyncProject, WriteConcern.SAFE);
                        kosyncProject = projectDAO.get(kosyncProject.get_id());
                        if (kosyncProject == null) {
                            httpStatus.setCode(HTTPStatusCode.GONE);
                            httpStatus.setDeveloperMessage("Project was written to DB but was not returned successfully");
                        } else {
                            httpStatus.setCode(HTTPStatusCode.RESOURCE_CREATED);
                            httpStatus.setDeveloperMessage("Project was successfully written to DB");
                        }
                    }
				} catch (Exception e) {
					kosyncProject = null;
					httpStatus.setCode(HTTPStatusCode.INTERNAL_SERVER_ERROR);
					httpStatus
							.setDeveloperMessage("Exception occured while writing to Project DB");
					debugInfo = ExceptionUtils.getFullStackTrace(e
							.fillInStackTrace());
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
			httpStatus.setDeveloperMessage("Error in submitted query!! Check models.KosyncProject.java file");
			debugInfo = ExceptionUtils.getFullStackTrace(e1.fillInStackTrace());
			e1.printStackTrace();
		}

		// 4. Stop stopwatch
		stopWatch.stop();
		// 5. Calculate final HTTP response
		metadata.setQTime(stopWatch.getTime());
		System.out.println("printing kosync poject");
		System.out.println(kosyncProject.getUsersId());
		HTTPResponse<KosyncProject, MetadataPostUser, String> httpResponse = new HTTPResponse<KosyncProject, MetadataPostUser, String>(
				httpStatus, metadata, kosyncProject, debugInfo);
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
