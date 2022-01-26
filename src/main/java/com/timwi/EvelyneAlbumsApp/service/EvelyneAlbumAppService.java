package com.timwi.EvelyneAlbumsApp.service;

import com.timwi.EvelyneAlbumsApp.connector.SpotifyConnector;
import com.timwi.EvelyneAlbumsApp.domain.dto.AlbumDto;
import com.timwi.EvelyneAlbumsApp.domain.spotify.Albums;
import com.timwi.EvelyneAlbumsApp.repository.IAlbumRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvelyneAlbumAppService {

    private final SpotifyConnector spotifyConnector;
    private final IAlbumRepo iAlbumDTORepo;

    public Albums searchAlbums(String artist, String album) {
        return spotifyConnector.searchAlbums(artist, album);
    }

    public void saveAlbum(AlbumDto albumDto) {
        iAlbumDTORepo.save(albumDto);
    }

    public AlbumDto getAlbum(String id) {
        return iAlbumDTORepo.findById(id).orElse(null);
    }

    public int markUnmarkFavorite(String id) {
        return iAlbumDTORepo.markUnmarkFavorite(id);
    }

    public int tagAlbum(String id, String tag) {
        return iAlbumDTORepo.tagAlbum(id, tag);
    }

    public void deleteAlbum(String id) {
        iAlbumDTORepo.deleteById(id);
    }
}
