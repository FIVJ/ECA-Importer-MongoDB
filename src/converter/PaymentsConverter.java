package converter;

import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.Map;
import model.Payments;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class PaymentsConverter {

    Logger logger = Logger.getLogger("Convert");

    public Map converterToMap(Payments payment) {
        logger.trace("Start Method");
        Map mapPayments = new HashMap();
        mapPayments.put("Action", payment.getAction());
        mapPayments.put("File", payment.getFile());
        mapPayments.put("Function", payment.getFunction());
        mapPayments.put("Month", payment.getMonth());
        mapPayments.put("NIS", payment.getNis());
        mapPayments.put("Beneficiary", payment.getBeneficiary());
        mapPayments.put("Program", payment.getProgram());
        mapPayments.put("Siafi", payment.getSiafi());
        mapPayments.put("City", payment.getCity());
        mapPayments.put("State", payment.getState());
        mapPayments.put("Region", payment.getRegion());
        mapPayments.put("Source", payment.getSource());
        mapPayments.put("SubFunction", payment.getSubFunction());
        mapPayments.put("Year", payment.getYear());
        mapPayments.put("Value", payment.getValue());
        logger.trace("Ended Method");
        return mapPayments;
    }

    public Payments converterToPayments(DBObject dbo) {
        logger.trace("Start Method");
        Payments payment = new Payments();
        payment.setId(dbo.get("_id").toString());
        payment.setAction((int) dbo.get("Action"));
        payment.setFile((String) dbo.get("File"));
        payment.setFunction((int) dbo.get("Function"));
        payment.setMonth((String) dbo.get("Month"));
        payment.setNis((String) dbo.get("NIS"));
        payment.setBeneficiary((String) dbo.get("Beneficiary"));
        payment.setProgram((int) dbo.get("Program"));
        payment.setSiafi((String) dbo.get("Siafi"));
        payment.setCity((String) dbo.get("City"));
        payment.setState((String) dbo.get("State"));
        payment.setRegion((String) dbo.get("Region"));
        payment.setSource((String) dbo.get("Source"));
        payment.setSubFunction((int) dbo.get("SubFunction"));
        payment.setYear((String) dbo.get("Year"));
        payment.setValue((Double) dbo.get("Value"));
        logger.trace("Ended Method");
        return payment;
    }

}
