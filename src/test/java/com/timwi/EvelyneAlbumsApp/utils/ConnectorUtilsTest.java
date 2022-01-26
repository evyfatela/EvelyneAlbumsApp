package com.timwi.EvelyneAlbumsApp.utils;

import org.junit.jupiter.api.Test;

import static com.timwi.EvelyneAlbumsApp.utils.ConnectorUtils.buildQuery;
import static org.assertj.core.api.Assertions.assertThat;

public class ConnectorUtilsTest {

    private static final String ARTIST = "myArtist";
    private static final String ALBUM = "myAlbum";

    @Test
    public void buildQueryArtistAndAlbum() {
        assertThat(buildQuery(ARTIST, ALBUM)).isEqualTo("artist:myArtist+album:myAlbum");
    }

    @Test
    public void buildQueryArtistOnly() {
        assertThat(buildQuery(ARTIST, null)).isEqualTo("artist:myArtist");
    }

    @Test
    public void buildQueryAlbumEmpty() {
        assertThat(buildQuery(ARTIST, "")).isEqualTo("artist:myArtist");
    }

    @Test
    public void buildQueryAlbumOnly() {
        assertThat(buildQuery(null, ALBUM)).isEqualTo("album:myAlbum");
    }

    @Test
    public void buildQueryArtistEmpty() {
        assertThat(buildQuery("", ALBUM)).isEqualTo("album:myAlbum");
    }

    @Test
    public void buildQueryBothNull() {
        assertThat(buildQuery(null, null)).isEqualTo("");
    }

    @Test
    public void buildQueryBothEmpty() {
        assertThat(buildQuery("", "")).isEqualTo("");
    }
}
