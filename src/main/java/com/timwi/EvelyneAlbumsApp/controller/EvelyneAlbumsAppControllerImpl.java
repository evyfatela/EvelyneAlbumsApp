package com.timwi.EvelyneAlbumsApp.controller;

import com.timwi.EvelyneAlbumsApp.domain.dto.AlbumDto;
import com.timwi.EvelyneAlbumsApp.domain.spotify.Albums;
import com.timwi.EvelyneAlbumsApp.service.EvelyneAlbumAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.timwi.EvelyneAlbumsApp.utils.LogUtils.buildString;
import static com.timwi.EvelyneAlbumsApp.utils.LogUtils.getSearchCriteriaString;
import static com.timwi.EvelyneAlbumsApp.utils.StreamUtils.collectionToStream;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EvelyneAlbumsAppControllerImpl implements EvelyneAlbumsAppController {

    private final EvelyneAlbumAppService service;

    @Override
    @GetMapping(value = "/albums/search")
    public ResponseEntity<List<AlbumDto>> searchAlbums(@RequestParam(required = false) String artist,
                                                       @RequestParam(required = false) String album) {
        log.info("Search album for the given criteria :" + getSearchCriteriaString(artist, album));
        Albums albums = service.searchAlbums(artist, album);
        List<AlbumDto> albumDtoList = collectionToStream(albums.getItems()).map(AlbumDto::new).collect(Collectors.toList());
        if (albumDtoList.isEmpty()) {
            log.info("No album found with the following criterias : " + getSearchCriteriaString(artist, album));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albumDtoList, HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/album", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveAlbum(@RequestBody AlbumDto albumDto) {
        log.info("Save album in app library : " +
                 "name = " + buildString("", albumDto.getName(), "no album name") +
                 ", artist = " + buildString("", albumDto.getArtist(), "no artist"));
        service.saveAlbum(albumDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/album")
    public ResponseEntity<AlbumDto> getAlbum(@RequestParam String id) {
        log.info("Get album from app library");
        AlbumDto albumDto = service.getAlbum(id);
        if (null == albumDto) {
            log.info("Album not found in library");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(albumDto, HttpStatus.OK);
    }

    @Override
    @PatchMapping(value = "/album/favorite")
    public ResponseEntity<Void> markUnmarkFavorite(@RequestParam String id) {
        log.info("Mark album in app library as favourite, or unmark it if it's already favourite");
        int i = service.markUnmarkFavorite(id);
        if (i == 0) {
            log.info("Album not found in library, favorite not updated");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PatchMapping(value = "/album/tag")
    public ResponseEntity<Void> tagAlbum(@RequestParam String id, @RequestParam String tag) {
        log.info("Add tag to album in app library");
        int i = service.tagAlbum(id, tag);
        if (i == 0) {
            log.info("Album not found in library, tag not added");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/album")
    public ResponseEntity<Void> deleteAlbum(@RequestParam String id) {
        log.info("Delete album from app library");
        try {
            service.deleteAlbum(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            log.info("Album not found in library, delete not applied");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
