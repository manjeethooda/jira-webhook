/**
 * this file specifies the class structure of kosync issue 
 * kosync issue has actual jira issue ID , jira issue URL to be saved 
 * in DB for future reference, other jira issue details are not saved
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
 * Class to store kosync issue
 * 
 * @author mhooda
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class kosyncIssue {

	@JsonIgnore
	@Id
	ObjectId _id = new ObjectId();

	String id = _id.toString();
	

	@Required
	@Indexed
	public String jiraId;
	
	
	@Required
	public String jiraURL;
	
	
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

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}
	
	public void setJiraURL(String jiraURL) {
		this.jiraURL = jiraURL;
	}

	public ObjectId get_id() {
		return this._id;
	}

	public String getId() {
		return this.id;
	}

	public String getJiraId(){
		return this.jiraId;
	}

	public String getJiraURL(){
		return this.jiraURL;
	}
}
