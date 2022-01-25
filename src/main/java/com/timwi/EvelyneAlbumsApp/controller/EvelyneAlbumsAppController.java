package com.timwi.EvelyneAlbumsApp.controller;

import com.timwi.EvelyneAlbumsApp.domain.spotify.Albums;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface EvelyneAlbumsAppController {

    Albums searchAlbums(String artist, String album);
}
