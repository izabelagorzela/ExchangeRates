package org.gorzela.exchange;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.URISyntaxException;


@Component
public class UriFactory {

    public static final String PROTOCOL = "http";
    public static final String HOST = "api.nbp.pl";
    public static final String PATH = "/api/exchangerates/rates/c/";

    public URI getUri(String pathPart1, String pathPart2, String pathPart3) throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(PROTOCOL);
        uriBuilder.setHost(HOST);
        uriBuilder.setPath(PATH + pathPart1 + "/" + pathPart2 + "/" + pathPart3);
        return uriBuilder.build();
    }
}
