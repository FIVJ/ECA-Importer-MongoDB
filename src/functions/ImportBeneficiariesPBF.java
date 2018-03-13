package functions;

import dao.BeneficiariesDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import model.Beneficiaries;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class ImportBeneficiariesPBF {

    Logger logger = Logger.getLogger("Functions");

    public void importBeneficiariesPBF() {
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
                        String nis = data[7];
                        String beneficiary = data[8].toUpperCase();

                        Map mapBeneficiary = new HashMap();
                        mapBeneficiary.put("NIS", nis);
                        Beneficiaries bf = new BeneficiariesDAO().findBeneficiary(mapBeneficiary);
                        if (bf == null) {
                            Beneficiaries nbeneficiary = new Beneficiaries();
                            nbeneficiary.setNis(nis);
                            nbeneficiary.setBeneficiary(beneficiary);
                            new BeneficiariesDAO().save(nbeneficiary);
                        }

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
}
