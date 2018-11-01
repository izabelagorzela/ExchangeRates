package org.gorzela.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class NbpApiReader {

    @Autowired
    public UriFactory uriFactory;

    public NBPResponse getData(String pathPart1, String pathPart2, String pathPart3) throws URISyntaxException {

        URI uri = uriFactory.getUri(pathPart1, pathPart2, pathPart3);
        ResponseEntity<NBPResponse> entity;
        RestTemplate restTemplate = new RestTemplate();

        try {
            entity = restTemplate.getForEntity(uri, NBPResponse.class);

        } catch (Exception ex) {
            System.out.println("Something wrong happened...");
            System.out.println(ex.getMessage());
            return null;
        }

        if (entity.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        return entity.getBody();
    }
}