package dao;

import com.mongodb.DBObject;
import converter.PaymentsConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Payments;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class PaymentsDAO extends EntityDao {

    Logger logger = Logger.getLogger("DAO");

    public PaymentsDAO() {
        super(Payments.class);
    }

    public void save(Payments payment) {
        logger.trace("Start Method");
        Map mapPayments = new PaymentsConverter().converterToMap(payment);
        save(mapPayments);
        logger.trace("Ended Method");
    }

    public void update(Payments oldPayment, Payments newPayment) {
        logger.trace("Start Method");
        Map query = new PaymentsConverter().converterToMap(oldPayment);
        Map map = new PaymentsConverter().converterToMap(newPayment);
        update(query, map);
        logger.trace("Ended Method");
    }

    public void delete(Payments payment) {
        logger.trace("Start Method");
        Map map = new PaymentsConverter().converterToMap(payment);
        delete(map);
        logger.trace("Ended Method");
    }

    public Payments findPayment(Map mapKeyValue) {
        logger.trace("Start Method");
        DBObject dbObject = findOne(mapKeyValue);
        if (dbObject == null) {
            logger.trace("Ended Method");
            return null;
        }
        Payments payments = new PaymentsConverter().converterToPayments(dbObject);
        logger.trace("Ended Method");
        return payments;
    }

    public List findPayments() {
        logger.trace("Start Method");
        List dbObject = findAll();
        List payments = new ArrayList();
        for (Object dbo : dbObject) {
            Payments payment = new PaymentsConverter().converterToPayments((DBObject) dbo);
            payments.add(payment);
        }
        logger.trace("Ended Method");
        return payments;
    }

    public List findPayments(Map mapKeyValue) {
        logger.trace("Start Method");
        List dbObject = findKeyValue(mapKeyValue);
        List payments = new ArrayList();
        for (Object dbo : dbObject) {
            Payments payment = new PaymentsConverter().converterToPayments((DBObject) dbo);
            payments.add(payment);
        }
        logger.trace("Ended Method");
        return payments;
    }

}
