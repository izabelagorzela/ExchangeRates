package org.gorzela.exchange;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.gorzela.exchange.nbpapi.NBPResponse;
import org.gorzela.exchange.nbpapi.NbpApiReader;
import org.gorzela.exchange.statistics.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ExchangeRatesDisplay implements CommandLineRunner {

    private static final int ARG_MINIMUM_ARGUMENTS_NUMBER = 3;
    private static final int ARG_CURRENCY_CODE_OR_HELP = 0;
    private static final int ARG_DATE_FROM = 1;
    private static final int ARG_DATE_TO = 2;
    private static final int ARG_CALCULATION_VARIANT = 3;

    private static final String MY_CALCULATION_VARIANT = "-m";
    private static final String HELP = "-h";
    private static final boolean MY_ALGORITHM = false;
    private static final boolean APACHE_ALGORITHM = true;

    @Autowired
    private NbpApiReader nbpApiReader;

    private boolean algorithmVersion = APACHE_ALGORITHM;

    @Override
    public void run(String[] args){

        double arithmeticMean;
        double standardDeviation;
        double[] bids;
        double[] asks;

        log.info("Application is running...");

        if(args.length == 1 && args[ARG_CURRENCY_CODE_OR_HELP].equals(HELP)) {

            showHelpInformation();
            return;
        }
        if (args.length < ARG_MINIMUM_ARGUMENTS_NUMBER) {

            showErrorInformation();
            return;
        }

        NBPResponse chargeData = nbpApiReader.getData(args[ARG_CURRENCY_CODE_OR_HELP], args[ARG_DATE_FROM], args[ARG_DATE_TO]);
        if (chargeData != null) {

            if (args.length > ARG_MINIMUM_ARGUMENTS_NUMBER && args[ARG_CALCULATION_VARIANT].equals(MY_CALCULATION_VARIANT)) {
                    algorithmVersion = MY_ALGORITHM;
            }

            bids = chargeData.extractBids();
            asks = chargeData.extractAsks();

            arithmeticMean = calculateMean(bids, algorithmVersion);
            standardDeviation = calculateStandardDeviation(asks, algorithmVersion);

            showResult(arithmeticMean, standardDeviation);
        }
    }


    private double calculateMean(double[] bids , boolean algorithmVersion) {

        Statistics statistics = new Statistics(bids);

        if(algorithmVersion == MY_ALGORITHM) {

            return statistics.myStandardArithmeticMean();
        }
        else {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(bids);
            return descriptiveStatistics.getMean();
        }
    }

    private double calculateStandardDeviation(double[] asks, boolean algorithmVersion) {

        Statistics statistics = new Statistics(asks);
        if(algorithmVersion == MY_ALGORITHM) {

            return statistics.myStandardStandardDeviation();
        }
        else {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(asks);
            return descriptiveStatistics.getStandardDeviation();
        }
    }

    private void showResult(double arithmeticMean, double standardDeviation) {

        if(algorithmVersion == APACHE_ALGORITHM) {
            System.out.println("Calculation performed with Apache library");
        }
        if(algorithmVersion == MY_ALGORITHM){
            System.out.println("Calculation performed with custom algorithm");
        }
        System.out.println(arithmeticMean + " : Purchase average");
        System.out.println(standardDeviation + " : Standard deviation");
    }

    private void showHelpInformation() {

        System.out.println("Required parameters: [code] [date from] [date to]");
        System.out.println("Example call: java -jar exchangerates-1.0-SNAPSHOT.jar USD 2018-10-01 2018-10-04");
        System.out.println("Optional parameter: -m. The calculation will be based on own algorithm");
    }

    private void showErrorInformation() {

        System.out.println("Not enough arguments");
        System.out.println("For help start the program with -h parameter");
    }
}
