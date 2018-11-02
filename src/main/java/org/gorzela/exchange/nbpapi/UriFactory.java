package org.gorzela.exchange.nbpapi;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


@Component
public class UriFactory {

    public static final String PROTOCOL = "http";
    public static final String HOST = "api.nbp.pl";
    public static final String PATH = "/api/exchangerates/rates/c/{p1}/{p2}/{p3}";

    public URI getUri(String pathPart1, String pathPart2, String pathPart3) {

        UriComponents uc = UriComponentsBuilder.newInstance()
                .scheme(PROTOCOL)
                .host(HOST)
                .path(PATH)
                .build()
                .expand(pathPart1, pathPart2, pathPart3)
                .encode();
        return uc.toUri();
    }
}
