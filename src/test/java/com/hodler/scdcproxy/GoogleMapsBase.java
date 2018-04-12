package com.hodler.scdcproxy;

import org.glassfish.jersey.client.ClientProperties;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScdcproxyApplication.class)
public abstract class GoogleMapsBase {

    @Value("${google.api.key}")
    private String apiKey;

    protected WebTarget webTarget;

    @Before
    public void init_http_client() {
        Client client = ClientBuilder.newClient();
        // Since we are asserting 302 in some contracts
        client.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.FALSE);

        ClientRequestFilter requestFilter = rc -> {
            String keyedUri = String.format("%s&key=%s", rc.getUri().toString(), apiKey);
            rc.setUri(URI.create(keyedUri));
        };
        client.register(requestFilter);

        webTarget = client.target("https://maps.googleapis.com");
    }

}
