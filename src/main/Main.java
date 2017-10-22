package main;

import functions.ImportPaymentsPBF;

/**
 *
 * @author tassio
 */
public class Main {

    public static void main(String[] args) {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("Main");

        //Import file to DB
        Thread impPBFAction = new Thread() {
            @Override
            public void run() {
                long StartTime = System.currentTimeMillis();
                ImportPaymentsPBF impDB = new ImportPaymentsPBF();
                impDB.importPaymentsPBF();
                System.gc();
                long EndTime = System.currentTimeMillis();
                long totalms = ((EndTime - StartTime));
                long totalsec = (totalms / 1000) % 60;
                long totalmin = (totalms / 60000) % 60;
                long totalh = (totalms / 3600000);
                System.out.println("Files Imports - Total time ('HHH':'mm':'ss'.'SSS'): " + String.format("%03d:%02d:%02d.%03d", totalh, totalmin, totalsec, totalms));
                System.exit(0);
            }
        };

        impPBFAction.start();
    }

}
