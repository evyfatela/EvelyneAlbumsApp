package com.timwi.EvelyneAlbumsApp.utils;

import org.junit.jupiter.api.Test;

import static com.timwi.EvelyneAlbumsApp.utils.LogUtils.buildString;
import static com.timwi.EvelyneAlbumsApp.utils.LogUtils.getSearchCriteriaString;
import static org.assertj.core.api.Assertions.assertThat;

public class LogUtilsTest {

    private static final String ARTIST = "myArtist";
    private static final String ALBUM = "myAlbum";

    private static final String PREFIX = "value = ";
    private static final String VALUE = "value";
    private static final String DEFAULT_VALUE = "no value";

    @Test
    public void testGetSearchCriteriaStringArtistAndAlbum() {
        assertThat(getSearchCriteriaString(ARTIST, ALBUM)).isEqualTo(" artist = myArtist and album = myAlbum");
    }

    @Test
    public void testGetSearchCriteriaStringArtistOnly() {
        assertThat(getSearchCriteriaString(ARTIST, null)).isEqualTo(" artist = myArtist and no album criteria");
    }

    @Test
    public void testGetSearchCriteriaStringArtistEmpty() {
        assertThat(getSearchCriteriaString(ARTIST, "")).isEqualTo(" artist = myArtist and no album criteria");
    }

    @Test
    public void testGetSearchCriteriaStringAlbumOnly() {
        assertThat(getSearchCriteriaString(null, ALBUM)).isEqualTo(" no artist criteria and album = myAlbum");
    }

    @Test
    public void testGetSearchCriteriaStringAlbumEmpty() {
        assertThat(getSearchCriteriaString("", ALBUM)).isEqualTo(" no artist criteria and album = myAlbum");
    }

    @Test
    public void testBuildStringWithValue() {
        assertThat(buildString(PREFIX, VALUE, DEFAULT_VALUE)).isEqualTo("value = value");
    }

    @Test
    public void testBuildStringValueNull() {
        assertThat(buildString(PREFIX, null, DEFAULT_VALUE)).isEqualTo("no value");
    }

    @Test
    public void testBuildStringValueEmpty() {
        assertThat(buildString(PREFIX, "", DEFAULT_VALUE)).isEqualTo("no value");
    }

    @Test
    public void testBuildStringPrefixEmpty() {
        assertThat(buildString("", VALUE, DEFAULT_VALUE)).isEqualTo("value");
    }

    @Test
    public void testBuildStringPrefixEmptyValueNull() {
        assertThat(buildString("", null, DEFAULT_VALUE)).isEqualTo("no value");
    }
}
