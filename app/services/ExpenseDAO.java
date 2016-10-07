package services;

import models.Expense;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * @author ashutosh
 *
 */
public class ExpenseDAO extends BasicDAO<Expense, ObjectId>  {
    
    String collectionName = null;
    
    /**
     * @param datastore
     */
    public ExpenseDAO(Datastore datastore) {
        super(Expense.class, datastore);
        
    }

    public Expense get(String id) {
        return super.get(new ObjectId(id));
    }
    
}
