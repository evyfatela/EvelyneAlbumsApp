package com.timwi.EvelyneAlbumsApp.connector;

import com.timwi.EvelyneAlbumsApp.domain.spotify.Albums;
import com.timwi.EvelyneAlbumsApp.domain.spotify.SearchResultAlbums;
import com.timwi.EvelyneAlbumsApp.properties.SpotifyProperties;
import com.timwi.EvelyneAlbumsApp.security.SpotifyAuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static com.timwi.EvelyneAlbumsApp.constant.SpotifyConstant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyConnector {

    private RestTemplate restTemplate;
    private final SpotifyProperties spotifyProperties;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new SpotifyAuthenticationInterceptor(spotifyProperties));
    }

    public Albums searchAlbums(String artist, String album) {
        log.info("Call spotify API to search album");
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(SPOTIFY_API_URI + SEARCH)
                .queryParam(LIMIT, 20)
                .queryParam(TYPE, ALBUM_TYPE)
                .queryParam(QUERY, buildQuery(artist, album)).build();
        SearchResultAlbums result = restTemplate.getForObject(builder.toUriString(), SearchResultAlbums.class);
        return Optional.ofNullable(result).map(SearchResultAlbums::getAlbums).orElse(new Albums());
    }

    private String buildQuery(String artist, String album) {
        StringBuilder builder = new StringBuilder();
        Optional.ofNullable(artist).ifPresent(s -> builder.append(QUERY_ARTIST).append(s));
        Optional.ofNullable(album).ifPresent(s -> {
            if (!builder.isEmpty()) {
                builder.append(QUERY_APPENDER);
            }
            builder.append(QUERY_ALBUM).append(s);
        });
        String query = builder.toString();
        log.debug("Call to Spotify search API - Build query param for artist and album : " + query);
        return query;
    }
}
