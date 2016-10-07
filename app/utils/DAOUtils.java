package utils;

/**
 * Created by ashutosh on 3/04/15.
 */

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import models.*;

import org.mongodb.morphia.Morphia;

import play.Logger;
import play.Play;
import services.ExpenseDAO;
import services.GroupDAO;
import services.UserDAO;
import services.IssueDAO;

import java.net.UnknownHostException;

public final class DAOUtils {

	public static MongoDBMorphia userMongo = null;
	public static MongoDBMorphia expenseMongo = null;
	public static MongoDBMorphia groupMongo = null;
	public static MongoDBMorphia issueMongo = null;
	public static UserDAO userDAO = null;
	public static ExpenseDAO expenseDAO = null;
	public static GroupDAO groupDAO = null;
	public static IssueDAO issueDAO = null;

	/**
	 * Connects to MongoDB based on the configuration settings.
	 * <p/>
	 * If the database is not reachable, an error message is written and the
	 * application continues.
	 */
	public static boolean connect() {
		connectToUserMongo();
		connectToExpenseMongo();
		connectToIssueMongo();
		return true;
	}

	/**
	 * Connects to User MongoDB database by reading the configuration file
	 * 
	 * @throws UnknownHostException
	 * 
	 */
	private static void connectToUserMongo() {
		Logger.info("Trying to connect to User DB");
		try {
			String host = Play.application().configuration()
					.getString("user.mongodb.uri.host");
			String port = Play.application().configuration()
					.getString("user.mongodb.uri.port");
			String db = Play.application().configuration()
					.getString("user.mongodb.uri.db");
			userMongo = new MongoDBMorphia(host, port, db);
			userMongo.morphia.map(User.class).map(LinkedAccount.class);
			userMongo.datastore.ensureIndexes();
			userMongo.datastore.ensureCaps();
		} catch (Exception e) {
			userMongo = null;
			e.printStackTrace();
		}
		if (userMongo != null) {
			Logger.info("Found DB instance at given address:port");
			try {
				userDAO = new UserDAO(DAOUtils.userMongo.datastore);
				userDAO.ensureIndexes();
				Logger.info("Connected to User DB");
			} catch (Exception e) {
				userDAO = null;
				Logger.info("Could not connect to User DB!!");
				e.printStackTrace();
			}
		} else {
			Logger.info("User DB instance not found at given address:port!!");
		}
	}
	
	/**
     * Connects to Expense MongoDB database by reading the configuration file
     * 
     * @throws UnknownHostException
     * 
     */
    private static void connectToExpenseMongo() {
        Logger.info("Trying to connect to Expense DB");
        try {
            String host = Play.application().configuration()
                    .getString("expense.mongodb.uri.host");
            String port = Play.application().configuration()
                    .getString("expense.mongodb.uri.port");
            String db = Play.application().configuration()
                    .getString("expense.mongodb.uri.db");
            expenseMongo = new MongoDBMorphia(host, port, db);
            expenseMongo.morphia.map(Expense.class).map(Balance.class);
            expenseMongo.datastore.ensureIndexes();
            expenseMongo.datastore.ensureCaps();
        } catch (Exception e) {
            expenseMongo = null;
            e.printStackTrace();
        }
        if (expenseMongo != null) {
            Logger.info("Found DB instance at given address:port");
            try {
                expenseDAO = new ExpenseDAO(DAOUtils.expenseMongo.datastore);
                expenseDAO.ensureIndexes();
                Logger.info("Connected to Expense DB");
            } catch (Exception e) {
                expenseDAO = null;
                Logger.info("Could not connect to Expense DB!!");
                e.printStackTrace();
            }
        } else {
            Logger.info("Expense DB instance not found at given address:port!!");
        }
    }
    
    /**
     * Connects to Group MongoDB database by reading the configuration file
     * 
     * @throws UnknownHostException
     * 
     */
    private static void connectToGroupMongo() {
        Logger.info("Trying to connect to Group DB");
        try {
            String host = Play.application().configuration()
                    .getString("group.mongodb.uri.host");
            String port = Play.application().configuration()
                    .getString("group.mongodb.uri.port");
            String db = Play.application().configuration()
                    .getString("group.mongodb.uri.db");
            groupMongo = new MongoDBMorphia(host, port, db);
            groupMongo.morphia.map(Group.class).map(Expense.class).map(Balance.class);
            groupMongo.datastore.ensureIndexes();
            groupMongo.datastore.ensureCaps();
        } catch (Exception e) {
            groupMongo = null;
            e.printStackTrace();
        }
        if (groupMongo != null) {
            Logger.info("Found DB instance at given address:port");
            try {
                groupDAO = new GroupDAO(DAOUtils.groupMongo.datastore);
                groupDAO.ensureIndexes();
                Logger.info("Connected to Group DB");
            } catch (Exception e) {
                groupDAO = null;
                Logger.info("Could not connect to Group DB!!");
                e.printStackTrace();
            }
        } else {
            Logger.info("Group DB instance not found at given address:port!!");
        }
    }

    /**
     * Connects to kosync Issue MongoDB database by reading the configuration file
     * 
     * @throws UnknownHostException
     * 
     */
     private static void connectToIssueMongo() {
     	Logger.info("Trying to connect to kosync Issue DB");
     	try {
     		String host = Play.application().configuration()
     				.getString("issue.mongodb.uri.host");
     		String port = Play.application().configuration()
     				.getString("issue.mongodb.uri.port");
     		String db = Play.application().configuration()
     				.getString("issue.mongodb.uri.db");
     		issueMongo = new MongoDBMorphia(host, port, db);
     		issueMongo.morphia.map(kosyncIssue.class);
     		issueMongo.datastore.ensureIndexes();
     		issueMongo.datastore.ensureCaps();
     	} catch (Exception e) {
     		issueMongo = null;
     		e.printStackTrace();
     	}
     	if (issueMongo != null) {
     		Logger.info("Found DB instance at given address:port");
     		try {
     			issueDAO = new IssueDAO(DAOUtils.issueMongo.datastore);
     			issueDAO.ensureIndexes();
     			Logger.info("Connected to issue DB");
     		} catch (Exception e) {
     			issueDAO = null;
     			Logger.info("Could not connect to Issue DB!!");
     			e.printStackTrace();
     		}
     	} else {
     		Logger.info("Issue DB instance not found at given address:port!!");
     	}
     }
	
    
    /**
     * Disconnect from MongoDB.
     */
    public static void disconnect() {
        userMongo.closeMongoConnection();
        expenseMongo.closeMongoConnection();
        groupMongo.closeMongoConnection();
    	issueMongo.closeMongoConnection();
    }

}
