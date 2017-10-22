package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import db.MongoConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class EntityDao implements IDao {

    Logger logger = Logger.getLogger("DAO");
    private final Class persistentClass;
    private final DBCollection dbCollection;

    public EntityDao(Class persistentClass) {
        this.persistentClass = persistentClass;
        this.dbCollection = MongoConnection.getInstance().getDB().getCollection(persistentClass.getSimpleName());
    }

    protected DBCollection getDbCollection() {
        return dbCollection;
    }

    @Override
    public void save(Map mapEntity) {
        logger.trace("Start Method");
        BasicDBObject document = new BasicDBObject(mapEntity);
        dbCollection.save(document);
        logger.trace("Ended Method");
        //System.out.println("Save :> " + document);
    }

    @Override
    public void update(Map mapQuery, Map mapEntity) {
        logger.trace("Start Method");
        BasicDBObject query = new BasicDBObject(mapQuery);
        BasicDBObject entity = new BasicDBObject(mapEntity);
        dbCollection.update(query, entity);
        logger.trace("Ended Method");
    }

    @Override
    public void delete(Map mapEntity) {
        logger.trace("Start Method");
        BasicDBObject entity = new BasicDBObject(mapEntity);
        dbCollection.remove(entity);
        logger.trace("Ended Method");
    }

    @Override
    public DBObject findOne(Map mapEntity) {
        logger.trace("Start Method");
        BasicDBObject query = new BasicDBObject(mapEntity);
        logger.trace("Ended Method");
        return dbCollection.findOne(query);
    }

    @Override
    public List findAll() {
        logger.trace("Start Method");
        List list = new ArrayList();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        logger.trace("Ended Method");
        return list;
    }

    @Override
    public List findKeyValue(Map keyValue) {
        logger.trace("Start Method");
        List list = new ArrayList();
        DBCursor cursor = dbCollection.find(new BasicDBObject(keyValue));
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        logger.trace("Ended Method");
        return list;
    }

}
