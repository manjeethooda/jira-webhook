package services;

import models.kosyncProject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import utils.DAOUtils;

/**
 * @author sanket
 *
 */
public class ProjectDAO extends BasicDAO<kosyncProject, ObjectId> {

	String collectionName = null;

	/**
	 * @param datastore
	 */
	public ProjectDAO(Datastore datastore) {
		super(kosyncProject.class, datastore);

	}

	public kosyncProject get(String id) {
		return super.get(new ObjectId(id));
	}

	
	public kosyncProject getByJiraId(String jiraId) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<kosyncProject> q = db.createQuery(kosyncProject.class);
            q.or(
                q.criteria("jiraId").equal(jiraId)
            );
            return q.get();
	}
	
	public kosyncProject getByJiraURL(String jiraURL) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<kosyncProject> q = db.createQuery(kosyncProject.class);
            q.or(
                q.criteria("jiraURL").equal(jiraURL)
            );
            return q.get();
	}

	/*public kosyncProject getByJiraUser(String jiraUser) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<kosyncProject> q = db.createQuery(kosyncProject.class);
            q.or(
                q.criteria("jiraUser").equal(jiraUser)
            );
            return q.get();
	}*/
	
}
