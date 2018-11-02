package org.gorzela.exchange.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
@Setter
@AllArgsConstructor
public class Statistics {

    private double[] prices;


    public double myStandardArithmeticMean() {

        double pricesSum = 0;
        for(double price: prices) {

            pricesSum = pricesSum + price;
        }
        return pricesSum / prices.length;
    }

    public double varianceCount() {

        double numerator = 0;
        for(double price: prices ) {

            numerator = numerator + (pow(price - myStandardArithmeticMean(),2));
        }

        return numerator / (prices.length - 1);
    }

    public double myStandardStandardDeviation() {

       if (prices.length == 1) {
           return 0;
        }
       else {

           return sqrt(varianceCount());
       }
    }
}




