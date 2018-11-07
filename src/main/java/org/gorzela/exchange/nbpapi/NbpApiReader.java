package org.gorzela.exchange.nbpapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;


@Component
public class NbpApiReader {

    @Autowired
    public UriFactory uriFactory;

    public NBPResponse getData(String pathPart1, String pathPart2, String pathPart3) {

        URI uri = uriFactory.getUri(pathPart1, pathPart2, pathPart3);
        ResponseEntity<NBPResponse> entity;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new NBPErrorHandler());

        try {
            entity = restTemplate.getForEntity(uri, NBPResponse.class);

        } catch (RestClientException ex) {

            return null;
        }

        if (entity.getStatusCode() != HttpStatus.OK) {

            return null;
        }

        return entity.getBody();
    }

    private class NBPErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {

            if (clientHttpResponse.getStatusCode() != HttpStatus.OK) {

                return true;
            }
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            System.out.println(clientHttpResponse.getStatusText());
            System.out.println("For help start the program with -h parameter");
            if(clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Today's data may not be available");
            }
        }
    }
}