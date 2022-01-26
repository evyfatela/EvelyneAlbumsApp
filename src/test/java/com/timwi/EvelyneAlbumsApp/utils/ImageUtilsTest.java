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
        assertThat(getThumbnailImageUrl(Arrays.asList(createImage(URL_1, 450),
                createImage(URL_2, 20),
                createImage(URL_3, 180)))).isEqualTo(Optional.of(URL_2));
    }

    private Image createImage(String url, Integer size) {
        Image image = new Image();
        image.setUrl(url);
        image.setHeight(size);
        image.setWidth(size);
        return image;
    }
}
