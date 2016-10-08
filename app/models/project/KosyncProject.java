/**
 * this file specifies the class structure of kosync project 
 * kosync project has actual jira project URL , project ID , auth Keys  
 * in DB for future reference, other jira project details are not saved
 *
 */
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.PrePersist;
import models.*;
//import com.fasterxml.jackson.databind.annotation;
//import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import services.UserDAO;
import utils.DAOUtils;
import utils.commonUtils;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Project;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to store kosync project
 * 
 * @author mhooda
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class KosyncProject {

	/**
	 * Fields
	 *
       	 */	
	@JsonIgnore
	@Id
	ObjectId _id = new ObjectId();

	String id = _id.toString();
	
	@Required	
	public String projectVendor;	
	
	@Required
	public ProjectInfo projectInfo;	

	@Required
	public String[] usersId;
		
	public String lastUpdatedAt = new DateTime(DateTimeZone.UTC).toString();
	public String createdAt = new DateTime(DateTimeZone.UTC).toString();


	@PrePersist
	void prePersist() {
		lastUpdatedAt = new DateTime(DateTimeZone.UTC).toString();
	}
	
	/**
	 * Setters
	 *
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
		this.id = _id.toString();
	}

	public void setId(String id) {
		this.id = id;
		this._id = new ObjectId(id);
	}

	public void setProjectVendor(String projectVendor) {
		this.projectVendor = projectVendor;
	}

	public void setProjectInfo(ProjectInfo projectInfo){
		this.projectInfo = projectInfo;
	}

	public void setUsersId(String[] usersId){
		this.usersId = usersId;
	}
	
	/**
	 * Getters
	 *
	 */
	public ObjectId get_id() {
		return this._id;
	}

	public String getId() {
		return this.id;
	}

	public String getProjectVendor() {
		return this.projectVendor;
	}

	public ProjectInfo getProjectInfo(){
		return this.projectInfo;
	}

	public String[] getUsersId(){
		return this.usersId;
	}
}
