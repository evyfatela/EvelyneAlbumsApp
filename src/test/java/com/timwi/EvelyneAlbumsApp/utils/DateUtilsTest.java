package com.timwi.EvelyneAlbumsApp.utils;

import com.timwi.EvelyneAlbumsApp.domain.spotify.ReleaseDatePrecision;
import org.junit.jupiter.api.Test;

import static com.timwi.EvelyneAlbumsApp.utils.DateUtils.convertIntoDateAccordingPrecision;
import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilsTest {

    @Test
    public void convertReleaseDateWithPrecisionDay() {
        assertThat(convertIntoDateAccordingPrecision("2021-05-26", ReleaseDatePrecision.day)).isEqualTo("2021-05-26");
    }

    @Test
    public void convertReleaseDateWithPrecisionMonth() {
        assertThat(convertIntoDateAccordingPrecision("2021-05", ReleaseDatePrecision.month)).isEqualTo("2021-05-01");
    }

    @Test
    public void convertReleaseDateWithPrecisionYear() {
        assertThat(convertIntoDateAccordingPrecision("2021", ReleaseDatePrecision.year)).isEqualTo("2021-01-01");
    }
}
