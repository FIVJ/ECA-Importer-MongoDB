package dao;

import com.mongodb.DBObject;
import converter.CitiesConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Cities;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class CitiesDAO extends EntityDao {

    Logger logger = Logger.getLogger("DAO");

    public CitiesDAO() {
        super(Cities.class);
    }

    public void save(Cities city) {
        logger.trace("Start Method");
        Map mapCities = new CitiesConverter().converterToMap(city);
        save(mapCities);
        logger.trace("Ended Method");
    }

    public void update(Cities oldPayment, Cities newPayment) {
        logger.trace("Start Method");
        Map query = new CitiesConverter().converterToMap(oldPayment);
        Map map = new CitiesConverter().converterToMap(newPayment);
        update(query, map);
        logger.trace("Ended Method");
    }

    public void delete(Cities city) {
        logger.trace("Start Method");
        Map map = new CitiesConverter().converterToMap(city);
        delete(map);
        logger.trace("Ended Method");
    }

    public Cities findCity(Map mapKeyValue) {
        logger.trace("Start Method");
        DBObject dbObject = findOne(mapKeyValue);
        if (dbObject == null) {
            logger.trace("Ended Method");
            return null;
        }
        Cities cities = new CitiesConverter().converterToCities(dbObject);
        logger.trace("Ended Method");
        return cities;
    }

    public List findCities() {
        logger.trace("Start Method");
        List dbObject = findAll();
        List cities = new ArrayList();
        for (Object dbo : dbObject) {
            Cities city = new CitiesConverter().converterToCities((DBObject) dbo);
            cities.add(city);
        }
        logger.trace("Ended Method");
        return cities;
    }

    public List findCities(Map mapKeyValue) {
        logger.trace("Start Method");
        List dbObject = findKeyValue(mapKeyValue);
        List cities = new ArrayList();
        for (Object dbo : dbObject) {
            Cities city = new CitiesConverter().converterToCities((DBObject) dbo);
            cities.add(city);
        }
        logger.trace("Ended Method");
        return cities;
    }

}
