package services;

import models.kosyncIssue;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import utils.DAOUtils;

/**
 * @author sanket
 *
 */
public class IssueDAO extends BasicDAO<kosyncIssue, ObjectId> {

	String collectionName = null;

	/**
	 * @param datastore
	 */
	public IssueDAO(Datastore datastore) {
		super(kosyncIssue.class, datastore);

	}

	public kosyncIssue get(String id) {
		return super.get(new ObjectId(id));
	}

	
	public kosyncIssue getByJiraId(String jiraId) {
	    Datastore db = DAOUtils.issueMongo.datastore;
            Query<kosyncIssue> q = db.createQuery(kosyncIssue.class);
            q.or(
                q.criteria("jiraId").equal(jiraId)
            );
            return q.get();
	}
	
	public kosyncIssue getByJiraURL(String jiraURL) {
	    Datastore db = DAOUtils.issueMongo.datastore;
            Query<kosyncIssue> q = db.createQuery(kosyncIssue.class);
            q.or(
                q.criteria("jiraURL").equal(jiraURL)
            );
            return q.get();
	}
}
