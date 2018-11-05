package org.gorzela.exchange.nbpapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;


@Component
public class NbpApiReader {

    @Autowired
    public UriFactory uriFactory;

    public NBPResponse getData(String pathPart1, String pathPart2, String pathPart3) {

        URI uri = uriFactory.getUri(pathPart1, pathPart2, pathPart3);
        ResponseEntity<NBPResponse> entity;
        RestTemplate restTemplate = new RestTemplate();

        try {
            entity = restTemplate.getForEntity(uri, NBPResponse.class);

        } catch (RestClientException ex) {

            System.out.println(ex.getMessage());
            if(ex instanceof HttpClientErrorException) {
                if(((HttpClientErrorException) ex).getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                    System.out.println("Today's data may not be available");
                }
            }
            showErrorInformation();
            return null;
        }

        if (entity.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        return entity.getBody();
    }

    private void showErrorInformation() {

        System.out.println("");
        System.out.println("NBP server returned an error code");
        System.out.println("For help start the program with -h parameter");
    }
}