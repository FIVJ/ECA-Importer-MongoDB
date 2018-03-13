package db;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class MongoConnection {

    Logger logger = Logger.getLogger("DAO");

    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DB_NAME = "ECA";

    private static MongoConnection uniqInstance;
    private static int mongoInstance = 1;

    private Mongo mongo;
    private DB db;

    private MongoConnection() {
    }

    public static synchronized MongoConnection getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new MongoConnection();
        }
        return uniqInstance;
    }

    public DB getDB() {
        if (mongo == null) {
            try {
                mongo = new Mongo(HOST, PORT);
                db = mongo.getDB(DB_NAME);
            } catch (Exception e) {
                logger.error("Unexpected error", e);
            }
        }
        return db;
    }

}
