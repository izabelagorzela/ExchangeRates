package org.gorzela.exchange;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ExchangeRatesDisplay implements CommandLineRunner {

    private static final int CURRENCY_CODE = 0;
    private static final int DATE_FROM = 1;
    private static final int DATE_TO = 2;
    private static final int MINIMUM_ARGUMENTS_NUMBER = 3;
    private static final int CALCULATION_VARIANT = 3;
    private static final String MY_CALCULATION_VARIANT = "-m";
    private static final boolean MY_ALGORITHM = false;
    private static final boolean APATCHE_ALGORITHM = true;

    @Autowired
    private NbpApiReader nbpApiReader;

    private boolean algorithmVersion = APATCHE_ALGORITHM;

    @Override
    public void run(String[] args) throws Exception {

        double arithmeticMean;
        double standardDeviation;
        double[] bids;
        double[] asks;

        log.info("Application is running...");

        if(args.length < MINIMUM_ARGUMENTS_NUMBER) {
            System.out.println("No enought arguments");
        }

        NBPResponse chargeData = nbpApiReader.getData(args[CURRENCY_CODE], args[DATE_FROM], args[DATE_TO]);
        if(chargeData != null) {

            if(args.length > MINIMUM_ARGUMENTS_NUMBER && args[CALCULATION_VARIANT].equals(MY_CALCULATION_VARIANT)) {
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

        if(algorithmVersion == APATCHE_ALGORITHM) {
            System.out.println("Calculation performed with Apache library");
        }
        if(algorithmVersion == MY_ALGORITHM){
            System.out.println("Calculation performed with custom algorithm");
        }
        System.out.println(arithmeticMean + " : Purchase average");
        System.out.println(standardDeviation + " : Standard deviation");


    }
}
