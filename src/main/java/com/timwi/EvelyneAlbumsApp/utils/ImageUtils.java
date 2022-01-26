package com.timwi.EvelyneAlbumsApp.utils;

import com.timwi.EvelyneAlbumsApp.domain.spotify.Image;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.timwi.EvelyneAlbumsApp.utils.StreamUtils.collectionToStream;

@Slf4j
public final class ImageUtils {

    private ImageUtils() {
    }

    public static byte[] getThumbnailBytes(List<Image> images) {
        return getThumbnailImageUrl(images).map(ImageUtils::getBytesFromUrl).orElse(null);
    }

    public static byte[] getBytesFromUrl(String url) {
        try (InputStream is = new URL(url).openStream()) {
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            log.error("An error occured while getting bytes from url : ", e);
            return null;
        }
    }

    public static Optional<String> getThumbnailImageUrl(List<Image> images) {
        return collectionToStream(images).min(Comparator.comparingInt(Image::getHeight))
                .map(Image::getUrl);
    }
}
