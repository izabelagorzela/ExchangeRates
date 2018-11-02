package org.gorzela.exchange.test;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.gorzela.exchange.statistics.Statistics;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    @Test
    public void arithmeticMeanSmallValuesTest() {

        double[] bids = {0.0073, 0.0023};
        Statistics statistics = new Statistics(bids);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(bids);
        assertEquals(descriptiveStatistics.getMean(), statistics.myStandardArithmeticMean(), 0.0000001);
    }

    @Test
    public void arithmeticMeanOneValueTest() {

        double[] bids = {3.7273};
        Statistics statistics = new Statistics(bids);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(bids);
        assertEquals(descriptiveStatistics.getMean(), statistics.myStandardArithmeticMean(), 0);
    }

    @Test
    public void arithmeticMeanTwoValuesTest() {

        double[] bids = {3.7273, 3.7626};
        Statistics statistics = new Statistics(bids);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(bids);
        assertEquals(descriptiveStatistics.getMean(), statistics.myStandardArithmeticMean(), 0);
    }

    @Test
    public void arithmeticMeanFiveValuesTest() {

        double[] bids = {3.7273,3.7258,3.7626, 3.4258,3.7626 };
        Statistics statistics = new Statistics(bids);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(bids);
        assertEquals(descriptiveStatistics.getMean(), statistics.myStandardArithmeticMean(), 0.0000001);
    }

    @Test
    public void arithmeticMeanTenValuesTest() {

        double[] bids = {3.7273, 3.7258, 3.7626, 3.7273, 3.7258, 3.7626, 3.4258, 3.7626, 3.7626, 3.4258};
        Statistics statistics = new Statistics(bids);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(bids);
        assertEquals(descriptiveStatistics.getMean(), statistics.myStandardArithmeticMean(), 0.0000001);
    }

    @Test
    public void StandardDeviationSmallValuesTest() {

        double[] asks = {0.0073, 0.0023};
        Statistics statistics = new Statistics(asks);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(asks);
        assertEquals(descriptiveStatistics.getStandardDeviation(), statistics.myStandardStandardDeviation(), 0.0000001);
    }

    @Test
    public void StandardDeviationOneValueTest() {

        double[] asks = {3.7273};
        Statistics statistics = new Statistics(asks);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(asks);
        assertEquals(descriptiveStatistics.getStandardDeviation(), statistics.myStandardStandardDeviation(), 0);
    }

    @Test
    public void StandardDeviationTwoValuesTest() {

        double[] asks = {3.7273, 3.7626};
        Statistics statistics = new Statistics(asks);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(asks);
        assertEquals(descriptiveStatistics.getStandardDeviation(), statistics.myStandardStandardDeviation(), 0);
    }

    @Test
    public void StandardDeviationFiveValuesTest() {

        double[] asks = {3.7273,3.7258,3.7626, 3.4258,3.7626};
        Statistics statistics = new Statistics(asks);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(asks);
        assertEquals(descriptiveStatistics.getStandardDeviation(), statistics.myStandardStandardDeviation(), 0.0000001);
    }

    @Test
    public void StandardDeviationTenValuesTest() {

        double[] asks = {3.7273, 3.7258, 3.7626, 3.7273, 3.7258, 3.7626, 3.4258, 3.7626, 3.7626, 3.4258};
        Statistics statistics = new Statistics(asks);
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(asks);
        assertEquals(descriptiveStatistics.getStandardDeviation(), statistics.myStandardStandardDeviation(), 0.0000001);
    }
}

