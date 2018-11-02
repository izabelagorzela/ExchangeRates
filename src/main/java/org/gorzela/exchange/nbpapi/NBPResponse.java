package org.gorzela.exchange.nbpapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NBPResponse {

      private String currency;
      private String code;
      private ArrayList<Rate> rates;

      public double[] extractBids() {

            double[] bids = new double[daysNumberCount()];
            int i = 0;
            for(Rate rate: rates) {
                  bids[i] = rate.getBid();
                  i ++;
            }
            return bids;
      }

      public double[] extractAsks() {

          double[] asks = new double[daysNumberCount()];
          int i = 0;
          for(Rate rate: rates) {
              asks[i] = rate.getAsk();
              i++;
          }
          return asks;
      }

      public int daysNumberCount() {

            return rates.size();
      }
}
