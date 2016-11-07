package services;



import java.util.ArrayList;
import java.util.List;
import models.KosyncIssue;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import utils.DAOUtils;

/**
 * @author sanket
 *
 */
public class IssueDAO extends BasicDAO<KosyncIssue, ObjectId> {

	String collectionName = null;

	/**
	 * @param datastore
	 */
	public IssueDAO(Datastore datastore) {
		super(KosyncIssue.class, datastore);

	}

	public KosyncIssue get(String id) {
		return super.get(new ObjectId(id));
	}

	
	public KosyncIssue getByProjectId(String projectId) {
	    Datastore db = DAOUtils.issueMongo.datastore;
            Query<KosyncIssue> q = db.createQuery(KosyncIssue.class);
            q.or(
                q.criteria("projectId").equal(projectId)
            );
            return q.get();
	}
	public KosyncIssue getByParent(String parentIssue) {
	    Datastore db = DAOUtils.issueMongo.datastore;
            Query<KosyncIssue> q = db.createQuery(KosyncIssue.class);
            q.or(
                q.criteria("parentIssue").equal(parentIssue)
            );
            return q.get();
	}
	
	public KosyncIssue getByChild(String childIssue) {
	    Datastore db = DAOUtils.issueMongo.datastore;
            Query<KosyncIssue> q = db.createQuery(KosyncIssue.class);
            q.or(
                q.criteria("childIssue").equal(childIssue)
            );
            return q.get();
	}

	public KosyncIssue getByProjectIdAndKey(String projectId, String key) {
	    Datastore db = DAOUtils.issueMongo.datastore;
            Query<KosyncIssue> q = db.createQuery(KosyncIssue.class);
	    q.and(
  		q.criteria("projectId").equal(projectId),
    		q.criteria("vendorKey").equal(key)
	    );
	    return q.get();
	}

	public List<KosyncIssue> getByKey(String key){
	    Datastore db = DAOUtils.issueMongo.datastore;
            List<KosyncIssue> list = db.createQuery(KosyncIssue.class)
   					.field("vendorKey").equal(key).asList();
	    //System.out.println(list);	
	    return list;		
	}
}
