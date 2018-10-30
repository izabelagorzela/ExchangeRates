package org.gorzela.exchange.test;

import org.gorzela.exchange.Statistics;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    private Statistics statistics;

    @Before
    public void setUpStatistics() {

        statistics = new Statistics();

    }

    @Test
    public void arithmeticMeanOneValuesTest() {

        double[] bidArray = {3.7273};
        statistics.setBidArray(bidArray);
        assertEquals(statistics.apacheStandardArithmeticMean(bidArray ), statistics.myStandardArithmeticMean(bidArray ), 0);
    }

    @Test
    public void arithmeticMeanFiveValuesTest() {

        double[] bidArray = {3.7273,3.7258,3.7626, 3.4258,3.7626 };
        statistics.setBidArray(bidArray);
        assertEquals(statistics.apacheStandardArithmeticMean(bidArray ), statistics.myStandardArithmeticMean(bidArray ), 0.0000001);
    }

    @Test
    public void arithmeticMeanTenValuesTest() {

        double[] bidArray = {3.7273, 3.7258, 3.7626, 3.7273, 3.7258, 3.7626, 3.4258, 3.7626, 3.7626, 3.4258};
        statistics.setBidArray(bidArray);
        assertEquals(statistics.apacheStandardArithmeticMean(bidArray ), statistics.myStandardArithmeticMean(bidArray ), 0.0000001);
    }

    @Test
    public void StandardDeviationOneValuesTest() {

        double[] askArray = {3.7273};
        statistics.setAskArray(askArray);
        assertEquals(statistics.apacheStandardStandardDeviation(), statistics.myStandardStandardDeviation(), 0);
    }
    @Test
    public void StandardDeviationFiveValuesTest() {

        double[] askArray = {3.7273,3.7258,3.7626, 3.4258,3.7626};
        statistics.setAskArray(askArray);
        assertEquals(statistics.apacheStandardStandardDeviation(), statistics.myStandardStandardDeviation(), 0.0000001);
    }

    @Test
    public void StandardDeviationTenValuesTest() {

        double[] askArray = {3.7273, 3.7258, 3.7626, 3.7273, 3.7258, 3.7626, 3.4258, 3.7626, 3.7626, 3.4258};
        statistics.setAskArray(askArray);
        assertEquals(statistics.apacheStandardStandardDeviation(), statistics.myStandardStandardDeviation(), 0.0000001);
    }
}

