package com.timwi.EvelyneAlbumsApp.utils;

import java.util.Optional;

public final class LogUtils {
    private LogUtils() {
    }

    public static String getSearchCriteriaString(String artist, String album) {
        return buildString(" artist = ", artist, " no artist criteria")
               + buildString(" and album = ", album, " and no album criteria");
    }

    public static String buildString(String prefix, String value, String emptyValue) {
        return Optional.ofNullable(value).filter(s -> !s.isEmpty())
                .map(s -> prefix + s).orElse(emptyValue);
    }
}
