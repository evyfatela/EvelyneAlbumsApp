package com.timwi.EvelyneAlbumsApp.controller;

import com.timwi.EvelyneAlbumsApp.connector.SpotifyConnector;
import com.timwi.EvelyneAlbumsApp.domain.spotify.Albums;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EvelyneAlbumsAppControllerImpl implements EvelyneAlbumsAppController {

    private final SpotifyConnector spotifyConnector;

    @Override
    @GetMapping(value = "/albums/search")
    public Albums searchAlbums(@RequestParam String artist, @RequestParam String album) {
        return spotifyConnector.searchAlbum(artist, album);
    }
}
