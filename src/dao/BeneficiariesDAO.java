package dao;

import com.mongodb.DBObject;
import converter.BeneficiariesConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Beneficiaries;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class BeneficiariesDAO extends EntityDao {

    Logger logger = Logger.getLogger("DAO");

    public BeneficiariesDAO() {
        super(Beneficiaries.class);
    }

    public void save(Beneficiaries payment) {
        logger.trace("Start Method");
        Map mapBeneficiaries = new BeneficiariesConverter().converterToMap(payment);
        save(mapBeneficiaries);
        logger.trace("Ended Method");
    }

    public void update(Beneficiaries oldPayment, Beneficiaries newPayment) {
        logger.trace("Start Method");
        Map query = new BeneficiariesConverter().converterToMap(oldPayment);
        Map map = new BeneficiariesConverter().converterToMap(newPayment);
        update(query, map);
        logger.trace("Ended Method");
    }

    public void delete(Beneficiaries payment) {
        logger.trace("Start Method");
        Map map = new BeneficiariesConverter().converterToMap(payment);
        delete(map);
        logger.trace("Ended Method");
    }

    public Beneficiaries findBeneficiary(Map mapKeyValue) {
        logger.trace("Start Method");
        DBObject dbObject = findOne(mapKeyValue);
        if (dbObject == null) {
            logger.trace("Ended Method");
            return null;
        }
        Beneficiaries beneficiaries = new BeneficiariesConverter().converterToBeneficiaries(dbObject);
        logger.trace("Ended Method");
        return beneficiaries;
    }

    public List findBeneficiaries() {
        logger.trace("Start Method");
        List dbObject = findAll();
        List beneficiaries = new ArrayList();
        for (Object dbo : dbObject) {
            Beneficiaries payment = new BeneficiariesConverter().converterToBeneficiaries((DBObject) dbo);
            beneficiaries.add(payment);
        }
        logger.trace("Ended Method");
        return beneficiaries;
    }

    public List findBeneficiaries(Map mapKeyValue) {
        logger.trace("Start Method");
        List dbObject = findKeyValue(mapKeyValue);
        List beneficiaries = new ArrayList();
        for (Object dbo : dbObject) {
            Beneficiaries payment = new BeneficiariesConverter().converterToBeneficiaries((DBObject) dbo);
            beneficiaries.add(payment);
        }
        logger.trace("Ended Method");
        return beneficiaries;
    }

}
