package com.timwi.EvelyneAlbumsApp.domain.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Artist {

    @JsonProperty(value = "external_urls")
    ExternalUrl externalUrls;

    String href;

    String id;

    String name;

    String type;

    String uri;

}
