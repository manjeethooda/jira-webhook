package services;

import models.Expense;
import models.Group;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * @author ashutosh
 *
 */
public class GroupDAO extends BasicDAO<Group, ObjectId>  {
    
    String collectionName = null;
    
    /**
     * @param datastore
     */
    public GroupDAO(Datastore datastore) {
        super(Group.class, datastore);
        
    }

    public Group get(String id) {
        return super.get(new ObjectId(id));
    }

}
