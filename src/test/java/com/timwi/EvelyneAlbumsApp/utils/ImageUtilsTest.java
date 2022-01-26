package com.timwi.EvelyneAlbumsApp.utils;

import com.timwi.EvelyneAlbumsApp.domain.spotify.Image;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static com.timwi.EvelyneAlbumsApp.utils.ImageUtils.getThumbnailImageUrl;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ImageUtilsTest {

    private static final String URL_1 = "url1";
    private static final String URL_2 = "url2";
    private static final String URL_3 = "url3";

    @Test
    public void testGetThumbnailImageUrl() {
        assertThat(getThumbnailImageUrl(Arrays.asList(Image.builder().height(450).width(450).url(URL_1).build(),
                Image.builder().height(20).width(20).url(URL_2).build(),
                Image.builder().height(180).width(180).url(URL_3).build()))).isEqualTo(Optional.of(URL_2));
    }
}
