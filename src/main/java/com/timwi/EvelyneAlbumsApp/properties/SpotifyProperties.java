package com.timwi.EvelyneAlbumsApp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spotify")
public class SpotifyProperties {

    private static final String SEPARATOR_COLON = ":";
    private String clientId;
    private String clientSecret;

    private String test;

    public String getClientIdAndSecret() {
        return clientId + SEPARATOR_COLON + clientSecret;
    }
}
