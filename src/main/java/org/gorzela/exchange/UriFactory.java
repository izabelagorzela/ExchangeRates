package org.gorzela.exchange;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.URISyntaxException;


@Component
public class UriFactory {

    public URI getUri(String pathPart1, String pathPart2, String pathPart3) throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.nbp.pl");
        uriBuilder.setPath("/api/exchangerates/rates/c/" + pathPart1 + "/" + pathPart2 + "/" + pathPart3);
        return uriBuilder.build();
    }
}
