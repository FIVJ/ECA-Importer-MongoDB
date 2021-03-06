package functions;

import dao.PaymentsDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        Arrays.sort(filesCSV);
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
                        switch (data[0].toUpperCase()) {
                            case "MG":
                            case "RJ":
                            case "SP":
                            case "ES":
                                region = "SUDESTE";
                                break;
                            case "PR":
                            case "RS":
                            case "SC":
                                region = "SUL";
                                break;
                            case "DF":
                            case "GO":
                            case "MT":
                            case "MS":
                                region = "CENTRO-OESTE";
                                break;
                            case "AM":
                            case "AC":
                            case "RO":
                            case "RR":
                            case "TO":
                            case "AP":
                                region = "NORTE";
                                break;
                            default:
                                region = "NORDESTE";
                                break;
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

                        Payments payment = new Payments(action, nis, beneficiary, siafi, city, state, region, file, month, year, function, program, source, subFunction, value);

                        new PaymentsDAO().save(payment);
                    }

                    if (totalimport % 10000 == 0) {
                        System.out.println("Imports = " + totalimport);
                        System.gc();
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
