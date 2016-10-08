/**
 * this file specifies the class structure of ticket(jira) project 
 *
 */
package models;


import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.PrePersist;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
 * Class for project info
 * 
 * @author mhooda
 * 
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInfo {

	/**
	 * Fields
	 *
       	 */	

	@JsonProperty("projectName")
	@Indexed
    	private String projectName;

	@JsonProperty("projectURL")
    	private String projectURL;

	@JsonProperty("authUser")
    	private String authUser;

	@JsonProperty("authKey")
    	private String authKey;

	/**
	 * Setters
	 *
	 */

	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	

	public void setProjectURL(String projectURL){
		this.projectURL = projectURL;
	}


	public void setAuthKey(String authKey){
		this.authKey = authKey;
	}
	

	public void setAuthUser(String authUser){
		this.authUser = authUser;
	}

	
	/**
	 * Getters
	 *
	 */

	public String getProjectName(){
		return this.projectName;
	}
	

	public String getProjectURL(){
		return this.projectURL; 
	}


	public String getAuthKey(){
		return this.authKey; 
	}
	

	public String getAuthUser(){
		return this.authUser;
	}

}


