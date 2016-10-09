/**
 * this file specifies the class structure of kosync project 
 * kosync project has actual jira project URL , project ID , auth Keys  
 * in DB for future reference, other jira project details are not saved
 *
 */
package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;

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
	private String projectKey;
	
	@Required	
	private String projectURL;
	
	@Required	
	private String authUser;
	
	@Required	
	private String authKey;

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

	public void setUsersId(String[] usersId){
		this.usersId = usersId;
	}
	
	public void setProjectKey(String projectKey){
		this.projectKey = projectKey;
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
	public ObjectId get_id() {
		return this._id;
	}

	public String getId() {
		return this.id;
	}

	public String getProjectVendor() {
		return this.projectVendor;
	}

	public String getProjectKey(){
		return this.projectKey;
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

	public String[] getUsersId(){
		return this.usersId;
	}
}
