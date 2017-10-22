package converter;

import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.Map;
import model.Cities;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class CitiesConverter {

    Logger logger = Logger.getLogger("Convert");

    public Map converterToMap(Cities city) {
        logger.trace("Start Method");
        Map mapPayments = new HashMap();
        mapPayments.put("City", city.getCity());
        mapPayments.put("Region", city.getRegion());
        mapPayments.put("Siafi", city.getSiafi());
        mapPayments.put("State", city.getState());
        logger.trace("Ended Method");
        return mapPayments;
    }

    public Cities converterToCities(DBObject dbo) {
        logger.trace("Start Method");
        Cities city = new Cities();
        city.setId(dbo.get("_id").toString());
        city.setSiafi(dbo.get("Siafi").toString());
        city.setCity(dbo.get("City").toString());
        city.setState(dbo.get("State").toString());
        city.setRegion(dbo.get("Region").toString());
        logger.trace("Ended Method");
        return city;
    }

}
