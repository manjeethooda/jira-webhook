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
public class kosyncProject {

	@JsonIgnore
	@Id
	ObjectId _id = new ObjectId();

	String id = _id.toString();
	

	@Required
	@Indexed
	public String jiraKey;
	
	
	@Required
	public String jiraURL;

	@Required
	public String jiraUser;
	
	@Required
	public String jiraAuthKey;	
	
	public String lastUpdatedAt = new DateTime(DateTimeZone.UTC).toString();
	public String createdAt = new DateTime(DateTimeZone.UTC).toString();


	@PrePersist
	void prePersist() {
		lastUpdatedAt = new DateTime(DateTimeZone.UTC).toString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
		this.id = _id.toString();
	}

	public void setId(String id) {
		this.id = id;
		this._id = new ObjectId(id);
	}

	public void setJiraKey(String jiraKey) {
		this.jiraKey = jiraKey;
	}
	
	public void setjiraURL(String jiraURL) {
		this.jiraURL = jiraURL;
	}
	
	public void setJiraUser(String jiraUser) {
		this.jiraUser = jiraUser;
	}

	public void setJiraAuthKey(String jiraAuthKey) {
		this.jiraAuthKey = jiraAuthKey;
	}

	public ObjectId get_id() {
		return this._id;
	}

	public String getId() {
		return this.id;
	}

	public String getJiraId(){
		return this.jiraKey;
	}

	public String getJiraURL(){
		return this.jiraURL;
	}

	public String getJiraUser(){
		return this.jiraUser;
	}

	public String getJiraAuth(){
		return this.jiraAuthKey;
	}
}
