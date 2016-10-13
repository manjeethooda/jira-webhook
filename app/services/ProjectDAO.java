package services;


import java.util.ArrayList;
import java.util.List;

import models.KosyncProject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import utils.DAOUtils;

/**
 * @author mhooda
 *
 */
public class ProjectDAO extends BasicDAO<KosyncProject, ObjectId> {

	String collectionName = null;

	/**
	 * @param datastore
	 */
	public ProjectDAO(Datastore datastore) {
		super(KosyncProject.class, datastore);

	}

	public KosyncProject get(String _id) {
		return super.get(new ObjectId(_id));
	}

	
	public KosyncProject getByProjectKey(String projectKey) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<KosyncProject> q = db.createQuery(KosyncProject.class);
            q.or(
                q.criteria("projectKey").equal(projectKey)
            );
            return q.get();
	}
	
	public KosyncProject getByProjectURL(String projectURL) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<KosyncProject> q = db.createQuery(KosyncProject.class);
            q.or(
                q.criteria("projectURL").equal(projectURL)
            );
            return q.get();
	}


	public List<KosyncProject> getByKey(String key){
	    Datastore db = DAOUtils.projectMongo.datastore;
            List<KosyncProject> list = db.createQuery(KosyncProject.class)
   					.field("projectKey").equal(key).asList();
//	    System.out.println(list);	
	    return list;		
	}	
	/*public KosyncProject getByJiraUser(String jiraUser) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<KosyncProject> q = db.createQuery(KosyncProject.class);
            q.or(
                q.criteria("jiraUser").equal(jiraUser)
            );
            return q.get();
	}*/
	
}
