package org.gorzela.exchange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

    private double[] bidArray;
    private double[] askArray;

    public double myStandardArithmeticMean(double arrayValue[]) {

        double bidSum = 0;
        for(double bid: arrayValue) {
            bidSum = bidSum + bid;
        }
        double result = bidSum / arrayValue.length ;
        return result;
    }

    public double apacheStandardArithmeticMean(double arrayValue[]) {

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(arrayValue);
        return descriptiveStatistics.getMean();
    }

    public double varianceCount() {

        double numerator = 0;
        for(double ask: askArray) {

            numerator = numerator + (pow(ask - myStandardArithmeticMean(askArray),2));
        }

        return numerator / (askArray.length - 1);
    }

    public double myStandardStandardDeviation() {

       if (askArray.length == 1) {
           return 0;
        }
       else {

           return sqrt(varianceCount());
       }
    }

    public double apacheStandardStandardDeviation() {

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(askArray);
        return descriptiveStatistics.getStandardDeviation();
    }
}




