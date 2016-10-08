package services;

import models.*;
import models.KosyncProject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import utils.DAOUtils;

/**
 * @author sanket
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

	public KosyncProject get(String id) {
		return super.get(new ObjectId(id));
	}

	
	public KosyncProject getByProjectInfo(ProjectInfo projectInfo) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<KosyncProject> q = db.createQuery(KosyncProject.class);
            q.or(
                q.criteria("projectName").equal(projectInfo.getProjectName())
            );
            return q.get();
	}
	
	/*public KosyncProject getByUser(String jiraProjectURL) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<KosyncProject> q = db.createQuery(KosyncProject.class);
            q.or(
                q.criteria("jiraProjectURL").equal(jiraProjectURL)
            );
            return q.get();
	}*/

	/*public KosyncProject getByJiraUser(String jiraUser) {
	    Datastore db = DAOUtils.projectMongo.datastore;
            Query<KosyncProject> q = db.createQuery(KosyncProject.class);
            q.or(
                q.criteria("jiraUser").equal(jiraUser)
            );
            return q.get();
	}*/
	
}
