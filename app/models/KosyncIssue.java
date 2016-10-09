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
public class KosyncIssue {
	/**
	 * Fields
	 *
       	 */	

	@JsonIgnore
	@Id
	ObjectId _id = new ObjectId();

	String id = _id.toString();
	

	@Required
	@Indexed
	public String projectId;
	
	
	@Required
	public String vendorKey;

	public String parentIssue;

	public String status;
	
	public String childIssue;
	
	public boolean resolved;	
	
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
	
	public void setVendorKey(String vendorKey) {
		this.vendorKey = vendorKey;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public void setParentIssue(String parentIssue){
		this.parentIssue = parentIssue;
	}

	public void setStatus(String status){
		this.status = status;
	}
	
	public void setChildIssue(String childIssue){
		this.childIssue = childIssue;
	}
	
	public void setResolved(boolean resolved){
		this.resolved = resolved;
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
	
	public String getVendorKey(){
		return this.vendorKey;
	}
	
	public String getProjectId() {
		return this.projectId;
	}
	
	public String getParentIssue(){
		return this.parentIssue;
	}

	public String getStatus(){
		return this.status;
	}
	
	public String getChildIssue(){
		return this.childIssue;
	}
	
	public boolean getResolved(){
		return this.resolved;
	}
}
