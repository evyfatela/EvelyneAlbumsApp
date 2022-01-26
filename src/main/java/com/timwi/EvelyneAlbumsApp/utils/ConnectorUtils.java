package com.timwi.EvelyneAlbumsApp.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.timwi.EvelyneAlbumsApp.constant.SpotifyConstant.*;

@Slf4j
public final class ConnectorUtils {

    private ConnectorUtils() {
    }

    public static String buildQuery(String artist, String album) {
        StringBuilder builder = new StringBuilder();
        Optional.ofNullable(artist).filter(s -> !s.isEmpty())
                .ifPresent(s -> builder.append(QUERY_ARTIST).append(s));
        Optional.ofNullable(album).filter(s -> !s.isEmpty())
                .ifPresent(s -> {
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
