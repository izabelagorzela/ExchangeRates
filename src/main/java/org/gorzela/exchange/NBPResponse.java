package org.gorzela.exchange;

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

      public double[] extractBidArray() {

            double[] bidArray = new double[daysNumberCount()];
            int i = 0;
            for(Rate rate: rates) {
                  bidArray[i] = rate.getBid();
                  i ++;
            }
            return bidArray;
      }

      public double[] extractAskArray() {

          double[] askArray = new double[daysNumberCount()];
          int i = 0;
          for(Rate rate: rates) {
              askArray[i] = rate.getAsk();
              i++;
          }
          return askArray;
      }

      public int daysNumberCount() {

            return rates.size();
      }
}
