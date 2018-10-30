package org.gorzela.exchange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;


@Slf4j
@Component
public class ExchangeRatesDisplay implements CommandLineRunner {


    @Autowired
    private UriFactory uriFactory;

    @Override
    public void run(String[] args) throws Exception {

        boolean algorithmVersion = true;

        log.info("Application is running...");


        NBPResponse chargeData = getData(args[0], args[1], args[2]);
        if(chargeData != null) {

            if(args.length > 3 && args[3].equals("-m")) {
                algorithmVersion = false;
            }
            showResult(chargeData, algorithmVersion);
        }
    }

    private NBPResponse getData(String pathPart1, String pathPart2, String pathPart3) throws URISyntaxException {

        URI uri = uriFactory.getUri(pathPart1, pathPart2, pathPart3);
        ResponseEntity<NBPResponse> entity;
        RestTemplate restTemplate = new RestTemplate();


        try {
            entity = restTemplate.getForEntity(uri, NBPResponse.class);

        }catch (Exception ex) {
            System.out.println("Something wrong happened...");
            System.out.println(ex.getMessage());
            return null;
        }

        if(entity.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        System.out.println(entity.getBody());
        return entity.getBody();
    }

    private void showResult(NBPResponse chargeData, boolean algorithmVersion) {

        Statistics statistics = new Statistics(chargeData.extractBidArray(), chargeData.extractAskArray());

        if(algorithmVersion == true) {
            System.out.println("Apache standard");
            System.out.println("Purchase average " + statistics.apacheStandardArithmeticMean(statistics.getBidArray()));
            System.out.println("StandardDeviation " + statistics.apacheStandardStandardDeviation());
        }
        if(algorithmVersion == false){
            System.out.println("My standard");
            System.out.println("Purchase average " + statistics.myStandardArithmeticMean(statistics.getBidArray()));
            System.out.println("StandardDeviation " + statistics.myStandardStandardDeviation());
        }
    }
}
