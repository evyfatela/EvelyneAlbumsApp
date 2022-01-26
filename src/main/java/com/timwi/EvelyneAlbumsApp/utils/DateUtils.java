package com.timwi.EvelyneAlbumsApp.utils;

import com.timwi.EvelyneAlbumsApp.domain.spotify.ReleaseDatePrecision;

import java.sql.Date;
import java.util.Optional;

public final class DateUtils {

    private static final String DAY_01 = "-01";
    private static final String MONTH_01_DAY_01 = "-01-01";

    private DateUtils() {
    }

    public static Date convertIntoDateAccordingPrecision(String date, ReleaseDatePrecision precision) {
        String dateWithPrecision = "9999-01-01";

        switch (precision) {
            case day -> dateWithPrecision = date;
            case month -> dateWithPrecision = date.concat(DAY_01);
            case year -> dateWithPrecision = date.concat(MONTH_01_DAY_01);
        }
        return Optional.ofNullable(dateWithPrecision).map(Date::valueOf).orElse(null);
    }
}
