package com.timwi.EvelyneAlbumsApp.utils;

import com.timwi.EvelyneAlbumsApp.domain.spotify.ReleaseDatePrecision;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.Optional;

@Slf4j
public final class DateUtils {

    private static final String DAY_01 = "-01";
    private static final String MONTH_01_DAY_01 = "-01-01";

    private DateUtils() {
    }

    public static Date convertIntoDateAccordingPrecision(String date, ReleaseDatePrecision precision) {
        String dateWithPrecision = "9999-01-01";

        switch (precision) {
            case day -> {
                dateWithPrecision = date;
                log.debug("Release date with day precision, we keep it as is");
            }
            case month -> {
                dateWithPrecision = date.concat(DAY_01);
                log.debug("Release date with month precision, we set the day to 01");
            }
            case year -> {
                dateWithPrecision = date.concat(MONTH_01_DAY_01);
                log.debug("Release date with year precision, we set month and day to 01");
            }
        }
        return Optional.ofNullable(dateWithPrecision).map(Date::valueOf).orElse(null);
    }
}
