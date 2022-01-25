package com.timwi.EvelyneAlbumsApp.domain.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Album {
    @JsonProperty(value = "album_type")
    AlbumType albumType;

    List<Artist> artists;

    @JsonProperty(value = "available_markets")
    List<String> availableMarkets;

    @JsonProperty(value = "external_urls")
    ExternalUrl externalUrls;

    String href;

    String id;

    List<Image> images;

    String name;

    @JsonProperty(value = "release_date")
    String releaseDate;

    @JsonProperty(value = "release_date_precision")
    ReleaseDatePrecision releaseDatePrecision;

    @JsonProperty(value = "total_tracks")
    Integer totalTracks;

    String type;

    String uri;
}
