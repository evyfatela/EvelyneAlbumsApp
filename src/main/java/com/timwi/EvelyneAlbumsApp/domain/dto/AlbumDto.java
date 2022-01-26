package com.timwi.EvelyneAlbumsApp.domain.dto;

import com.timwi.EvelyneAlbumsApp.domain.spotify.Album;
import com.timwi.EvelyneAlbumsApp.domain.spotify.Artist;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Date;
import java.util.stream.Collectors;

import static com.timwi.EvelyneAlbumsApp.utils.DateUtils.convertIntoDateAccordingPrecision;
import static com.timwi.EvelyneAlbumsApp.utils.ImageUtils.getThumbnailBytes;
import static com.timwi.EvelyneAlbumsApp.utils.StreamUtils.collectionToStream;

@Data
@NoArgsConstructor
@Entity(name = "Album")
public class AlbumDto {

    @Id
    private String id;

    private String name;

    private Date releaseDate;

    private Integer totalTracks;

    private String artist;

    @Lob
    private byte[] image;

    private boolean favorite = false;

    private String tag;

    public AlbumDto(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.releaseDate = convertIntoDateAccordingPrecision(album.getReleaseDate(), album.getReleaseDatePrecision());
        this.totalTracks = album.getTotalTracks();
        this.artist = collectionToStream(album.getArtists()).map(Artist::getName).collect(Collectors.joining(","));
        this.image = getThumbnailBytes(album.getImages());
    }
}
