package functions;

import dao.CitiesDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import model.Cities;
import org.apache.log4j.Logger;

/**
 *
 * @author tassio
 */
public class ImportCitiesPBF {

    Logger logger = Logger.getLogger("Functions");

    public void importCities() {
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

                        String siafi = data[1];
                        String city = data[2].toUpperCase();
                        String state = data[0].toUpperCase();

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
