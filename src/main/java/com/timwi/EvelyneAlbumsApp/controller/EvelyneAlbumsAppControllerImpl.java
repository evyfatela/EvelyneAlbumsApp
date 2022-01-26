package com.timwi.EvelyneAlbumsApp.controller;

import com.timwi.EvelyneAlbumsApp.domain.dto.AlbumDto;
import com.timwi.EvelyneAlbumsApp.domain.spotify.Albums;
import com.timwi.EvelyneAlbumsApp.service.EvelyneAlbumAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.timwi.EvelyneAlbumsApp.utils.StreamUtils.collectionToStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EvelyneAlbumsAppControllerImpl implements EvelyneAlbumsAppController {

    private final EvelyneAlbumAppService service;

    @Override
    @GetMapping(value = "/albums/search")
    public ResponseEntity<List<AlbumDto>> searchAlbums(@RequestParam(required = false) String artist,
                                                       @RequestParam(required = false) String album) {
        Albums albums = service.searchAlbums(artist, album);
        List<AlbumDto> albumDtoList = collectionToStream(albums.getItems()).map(AlbumDto::new).collect(Collectors.toList());
        if (albumDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albumDtoList, HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/album", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveAlbum(@RequestBody AlbumDto albumDto) {
        service.saveAlbum(albumDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/album")
    public ResponseEntity<AlbumDto> getAlbum(@RequestParam String id) {
        AlbumDto albumDto = service.getAlbum(id);
        if (null == albumDto) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(albumDto, HttpStatus.OK);
    }

    @Override
    @PatchMapping(value = "/album/favorite")
    public ResponseEntity<Void> markUnmarkFavorite(@RequestParam String id) {
        int i = service.markUnmarkFavorite(id);
        if (i == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PatchMapping(value = "/album/tag")
    public ResponseEntity<Void> tagAlbum(@RequestParam String id, @RequestParam String tag) {
        int i = service.tagAlbum(id, tag);
        if (i == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/album")
    public ResponseEntity<Void> deleteAlbum(@RequestParam String id) {
        try {
            service.deleteAlbum(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
