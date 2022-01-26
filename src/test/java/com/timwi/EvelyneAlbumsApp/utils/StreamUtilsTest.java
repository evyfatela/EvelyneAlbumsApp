package com.timwi.EvelyneAlbumsApp.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.timwi.EvelyneAlbumsApp.utils.StreamUtils.collectionToStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamUtilsTest {

    private static final String ELM_1= "eml1";
    private static final String ELM_2= "eml2";
    private static final String ELM_3= "eml3";

    @Test
    public void testCollectionToStream() {
        assertTrue(collectionToStream(Arrays.asList(ELM_1, ELM_2, ELM_3)).findAny().isPresent());
    }

    @Test
    public void testCollectionToStreamEmpty() {
        assertTrue(collectionToStream(null).findAny().isEmpty());
    }
}
