package functions;

import dao.BeneficiariesDAO;
import dao.CitiesDAO;
import dao.PaymentsDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.Beneficiaries;
import model.Cities;
import model.Payments;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class ImportPaymentsPBF {

    Logger logger = Logger.getLogger("Functions");

    public void importPaymentsPBF() {
        logger.trace("Starting Method importPaymentsPBF");
        File fInput = new File("/Users/tassio/NetBeansProjects/ECA-Importer-MongoDB/CSV/Pagamentos");
        BufferedReader br = null;
        String line = "";
        String csvDivisor = "\t";
        File[] filesCSV = fInput.listFiles();

        for (File filesCSV1 : filesCSV) {
            long StartTime = System.currentTimeMillis();
            File fileCSV = filesCSV1;
            long totalimport = 0;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fInput + "/" + fileCSV.getName()), "ISO-8859-1"));
                System.out.println(fileCSV.getName());
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(csvDivisor);
                    if (totalimport != 0) {
                        String region;
                        if (data[0].toUpperCase().equals("MG") || data[0].toUpperCase().equals("RJ") || data[0].toUpperCase().equals("SP") || data[0].toUpperCase().equals("ES")) {
                            region = "SUDESTE";
                        } else if (data[0].toUpperCase().equals("PR") || data[0].toUpperCase().equals("RS") || data[0].toUpperCase().equals("SC")) {
                            region = "SUL";
                        } else if (data[0].toUpperCase().equals("DF") || data[0].toUpperCase().equals("GO") || data[0].toUpperCase().equals("MT") || data[0].toUpperCase().equals("MS")) {
                            region = "CENTRO-OESTE";
                        } else if (data[0].toUpperCase().equals("AM") || data[0].toUpperCase().equals("AC") || data[0].toUpperCase().equals("RO") || data[0].toUpperCase().equals("RR") || data[0].toUpperCase().equals("TO") || data[0].toUpperCase().equals("AP")) {
                            region = "NORTE";
                        } else {
                            region = "NORDESTE";
                        }

                        int action = Integer.parseInt(data[6]);
                        String nis = data[7];
                        String beneficiary = data[8].toUpperCase();
                        String siafi = data[1];
                        String city = data[2].toUpperCase();
                        String file = fileCSV.getName().toUpperCase();
                        String month = fileCSV.getName().substring(4, 6);
                        String year = fileCSV.getName().substring(0, 4);
                        int function = Integer.parseInt(data[3]);
                        int program = Integer.parseInt(data[5]);
                        String source = data[9].toUpperCase();
                        String state = data[0].toUpperCase();
                        int subFunction = Integer.parseInt(data[4]);
                        double value = Double.parseDouble(data[10]);

                        Map mapCity = new HashMap();
                        mapCity.put("Siafi", siafi);
                        Cities ct = new CitiesDAO().findCity(mapCity);
                        if (ct == null) {
                            Cities nCity = new Cities();
                            nCity.setSiafi(siafi);
                            nCity.setRegion(region);
                            nCity.setState(state);
                            nCity.setCity(city);
                            new CitiesDAO().save(nCity);
                        }

                        Map mapBeneficiary = new HashMap();
                        mapBeneficiary.put("NIS", nis);
                        Beneficiaries bf = new BeneficiariesDAO().findBeneficiary(mapBeneficiary);
                        if (bf == null) {
                            Beneficiaries nbeneficiary = new Beneficiaries();
                            nbeneficiary.setNis(nis);
                            nbeneficiary.setBeneficiary(beneficiary);
                            new BeneficiariesDAO().save(nbeneficiary);
                        }

                        Payments payment = new Payments(action, nis, siafi, file, month, year, function, program, source, subFunction, value);

                        new PaymentsDAO().save(payment);
                    }

                    if (totalimport % 10000 == 0) {
                        System.out.println("Imports = " + totalimport);
                    }

                    totalimport++;

                }
                long EndTime = System.currentTimeMillis();
                long totalms = ((EndTime - StartTime));
                long totalsec = (totalms / 1000) % 60;
                long totalmin = (totalms / 60000) % 60;
                long totalh = (totalms / 3600000);
                System.out.println("File " + fileCSV.getName() + " - Total time ('HHH':'mm':'ss'.'SSSSS'): " + String.format("%03d:%02d:%02d.%03d", totalh, totalmin, totalsec, totalms));
            } catch (FileNotFoundException e) {
                logger.error("Unexpected error", e);
            } catch (IOException e) {
                logger.error("Unexpected error", e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                        logger.trace("Ended Method importPaymentsPBF");
                    } catch (IOException e) {
                        logger.error("Unexpected error", e);
                    }
                }
            }
        }
    }

    public void updatePaymentsPBF(Payments payment, Payments newpayment) {
        Map map = new HashMap();
        map.put("_id", payment.getId());
        Payments query = new PaymentsDAO().findPayment(map);

        new PaymentsDAO().update(query, newpayment);

        Payments newPayments = new PaymentsDAO().findPayment(map);
        System.out.printf("Old:> " + query + "\nNew:> " + newPayments.toString());
    }

    public void deletePaymentsPBF(String Id) {
        Map map = new HashMap();
        map.put("_id", Id);
        List query = new PaymentsDAO().findPayments(map);

        for (Iterator it = query.iterator(); it.hasNext();) {
            Payments payment = (Payments) it.next();
            new PaymentsDAO().delete(payment);
        }

    }

    public void searchPaymentsPBF() {
        List payments = new PaymentsDAO().findPayments();
        for (Iterator it = payments.iterator(); it.hasNext();) {
            Payments payment = (Payments) it.next();
            System.out.println(payment.toString());
        }
    }

}
