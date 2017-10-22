package converter;

import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.Map;
import model.Beneficiaries;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class BeneficiariesConverter {

    Logger logger = Logger.getLogger("Convert");

    public Map converterToMap(Beneficiaries beneficiary) {
        logger.trace("Start Method");
        Map mapPayments = new HashMap();
        mapPayments.put("Beneficiary", beneficiary.getBeneficiary());
        mapPayments.put("NIS", beneficiary.getNis());
        logger.trace("Ended Method");
        return mapPayments;
    }

    public Beneficiaries converterToBeneficiaries(DBObject dbo) {
        logger.trace("Start Method");
        Beneficiaries beneficiary = new Beneficiaries();
        beneficiary.setId(dbo.get("_id").toString());
        beneficiary.setBeneficiary((String) dbo.get("Beneficiary"));
        beneficiary.setNis((String) dbo.get("NIS"));
        logger.trace("Ended Method");
        return beneficiary;
    }

}
