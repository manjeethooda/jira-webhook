package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Expense;
import models.User;
import models.restapi.ExpenseGetForm;
import models.restapi.HTTPResponse;
import models.restapi.HTTPStatus;
import models.restapi.HTTPStatusCode;
import models.restapi.Metadata;
import models.restapi.MetadataGetCollection;
import models.restapi.MetadataPostUser;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.StopWatch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;

import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ExpenseDAO;
import utils.DAOUtils;

public class Expenses extends Controller {
    static ExpenseDAO expenseDAO = DAOUtils.expenseDAO;
    
    public static Result getExpenseById(String id) {
        // 1. Start stopwatch
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 2. Initialize http response objects
        HTTPStatus httpStatus = new HTTPStatus();
        Expense expense = null;
        Metadata metadata = new Metadata();
        String debugInfo = null;

        // 3. Calculate response
        if (isInvalidExpenseId(id)) {
            httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
            httpStatus
                    .setDeveloperMessage("Expense id invalid. Make sure id is not empty or null. Also check if its a valid UUID");
        } else if (expenseDAO == null) {
            httpStatus.setCode(HTTPStatusCode.GONE);
            httpStatus.setDeveloperMessage("Not connected to Expense DB");
        } else {
            try {
                expense = expenseDAO.get(id);
                if (expense == null) {
                    httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
                    httpStatus.setDeveloperMessage("Expense not found in DB");
                } else {
                    httpStatus.setCode(HTTPStatusCode.OK);
                    httpStatus.setDeveloperMessage("Expense found in DB");
                }
            } catch (Exception e) {
                httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
                httpStatus
                        .setDeveloperMessage("Expense not found. \n"
                                + "Either id is invalid or expense doesnot exist in database. \n"
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
        HTTPResponse<Expense, Metadata, String> httpResponse = new HTTPResponse<Expense, Metadata, String>(
                httpStatus, metadata, expense, debugInfo);
        return status(httpStatus.code, Json.toJson(httpResponse));
    }
    
    public static Result getExpenses() {
        // 1. Start stopwatch
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 2. Initialize http response objects
        HTTPStatus httpStatus = new HTTPStatus();
        MetadataGetCollection metadata = new MetadataGetCollection();
        List<Expense> expenses = new ArrayList<Expense>();
        String debugInfo = null;

        // 3. Calculate response
        DBObject dbObjQuery = null;
        ExpenseGetForm expenseGetForm = null;
        Integer numFound = 0;

        try {
            Form<ExpenseGetForm> form = Form.form(ExpenseGetForm.class);
            if(form.hasErrors()) {
                throw new Exception("Form has errors");
            }
            expenseGetForm = form.bindFromRequest().get();

            if(expenseDAO==null) {
                // if not connected to Expense DB
                httpStatus.setCode(HTTPStatusCode.GONE);
                httpStatus.setDeveloperMessage("Not connected to Expense DB");
            } else {
                /*if(expenseGetForm.action != null && expenseGetForm.action.equalsIgnoreCase("deleteall")) {
                    expenseDAO.getDatastore().delete(expenseDAO.getDatastore().createQuery(Expense.class).filter("isActive", true));
                    httpStatus.setCode(HTTPStatusCode.OK);
                    httpStatus.setDeveloperMessage("Expenses were successfully deleted from DB");
                } else*/
                if (expenseGetForm.q == null || expenseGetForm.q == "") {
                    httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
                    httpStatus.setDeveloperMessage("Request Query invalid. Make sure query is not empty or null.");
                } else if (expenseGetForm.method.equals("basic")) {
                    // do nothing as of now
                } else if (expenseGetForm.method.equals("generic")) {
                    try {
                        dbObjQuery = (DBObject) JSON.parse(expenseGetForm.q);
                        DBObject dbObjSortQuery = (DBObject) JSON.parse(expenseGetForm.sort);
                        List<DBObject> expenseDBObject = expenseDAO.getCollection().find(dbObjQuery).sort(dbObjSortQuery).skip(expenseGetForm.start).limit(expenseGetForm.rows).toArray();
                        numFound = expenseDAO.getCollection().find(dbObjQuery).count();
                        expenses = new ObjectMapper().readValue(expenseDBObject.toString(), new TypeReference<List<Expense>>() {});
                        httpStatus.setCode(HTTPStatusCode.OK);
                        httpStatus.setDeveloperMessage("Query executed successfully.");
                    } catch (Exception e) {
                        httpStatus.setCode(HTTPStatusCode.NOT_FOUND);
                        httpStatus.setDeveloperMessage("Expense not found. "
                                + "Either id is invalid or expense doesnot exist in database. "
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
            httpStatus.setDeveloperMessage("Error in submitted query!! Check models.ExpenseGetForm.java file");
            debugInfo = ExceptionUtils.getFullStackTrace(e.fillInStackTrace());
            e.printStackTrace();
        }

        // 4. Stop stopwatch
        stopWatch.stop();

        // 5. Calculate final HTTP response
        metadata.setQTime(stopWatch.getTime());
        metadata.setNumFound(numFound);
        HTTPResponse<List<Expense>, MetadataGetCollection, String> httpResponse = new HTTPResponse<List<Expense>, MetadataGetCollection, String>(httpStatus, metadata, expenses, debugInfo);
        return status(httpStatus.code, Json.toJson(httpResponse));
    }
    
    public static Result postExpenses() {
        // 1. Start stopwatch
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 2. Initialize http response objects
        HTTPStatus httpStatus = new HTTPStatus();
        Expense expense = null;
        Metadata metadata = new Metadata();
        String debugInfo = null;

        // 3. Calculate response
        Form<Expense> form;
        try {
            form = Form.form(Expense.class);
            if (form.hasErrors()) {
                throw new Exception("Form has errors");
            }
            expense = form.bindFromRequest().get();
            if (expenseDAO == null) {
                // if not connected to Users DB
                httpStatus.setCode(HTTPStatusCode.GONE);
                httpStatus.setDeveloperMessage("Not connected to Expense DB");
            } else {
                try {
                    if (expenseDAO.get(expense.get_id()) != null) {
                        httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
                        httpStatus.setDeveloperMessage("Expense with same id already exists");
                    } else {
                        expenseDAO.save(expense, WriteConcern.SAFE);
                        expense = expenseDAO.get(expense.get_id());
                        if (expense == null) {
                            httpStatus.setCode(HTTPStatusCode.GONE);
                            httpStatus.setDeveloperMessage("Expense was written to DB but was not returned successfully");
                        } else {
                            httpStatus.setCode(HTTPStatusCode.RESOURCE_CREATED);
                            httpStatus.setDeveloperMessage("Expense was successfully written to DB");
                        }
                    }
                } catch (Exception e) {
                    expense = null;
                    httpStatus.setCode(HTTPStatusCode.INTERNAL_SERVER_ERROR);
                    httpStatus
                            .setDeveloperMessage("Exception occured while writing to Expense DB");
                    debugInfo = ExceptionUtils.getFullStackTrace(e
                            .fillInStackTrace());
                    e.printStackTrace();
                }
            }
        } catch (Exception e1) {
            httpStatus.setCode(HTTPStatusCode.BAD_REQUEST);
            httpStatus.setDeveloperMessage("Error in submitted query!! Check models.Expense.java file");
            debugInfo = ExceptionUtils.getFullStackTrace(e1.fillInStackTrace());
            e1.printStackTrace();
        }

        // 4. Stop stopwatch
        stopWatch.stop();
        // 5. Calculate final HTTP response
        metadata.setQTime(stopWatch.getTime());
        HTTPResponse<Expense, Metadata, String> httpResponse = new HTTPResponse<Expense, Metadata, String>(
                httpStatus, metadata, expense, debugInfo);
        return status(httpStatus.code, Json.toJson(httpResponse));
    }
    
    /**
     * @param id
     * @return
     */
    private static boolean isInvalidExpenseId(String id) {
        return id.isEmpty() || id == null;
    }

}
