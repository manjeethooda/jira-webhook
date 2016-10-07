package services;

import models.User;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import utils.DAOUtils;

/**
 * @author sanket
 *
 */
public class UserDAO extends BasicDAO<User, ObjectId> {

	String collectionName = null;

	/**
	 * @param datastore
	 */
	public UserDAO(Datastore datastore) {
		super(User.class, datastore);

	}

	public User get(String id) {
		return super.get(new ObjectId(id));
	}

	public User getByEmail(String email) {
		Datastore db = DAOUtils.userMongo.datastore;
		Query<User> q = db.createQuery(User.class);
		q.or(
				q.criteria("emailList").equal(email),
				q.criteria("email").equal(email)
		);
		return q.get();
	}
	
	public User getByPhone(String number) {
	    Datastore db = DAOUtils.userMongo.datastore;
        Query<User> q = db.createQuery(User.class);
        q.or(
                q.criteria("phoneNumber").equal(number)
        );
        return q.get();
	}
}
