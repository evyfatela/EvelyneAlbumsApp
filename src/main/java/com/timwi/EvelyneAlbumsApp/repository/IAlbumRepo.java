package com.timwi.EvelyneAlbumsApp.repository;

import com.timwi.EvelyneAlbumsApp.domain.dto.AlbumDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IAlbumRepo extends JpaRepository<AlbumDto, String> {

    @Modifying
    @Transactional
    @Query("update Album set favorite = CASE favorite WHEN TRUE THEN FALSE ELSE TRUE END where id = :id")
    int markUnmarkFavorite(@Param(value = "id") String id);

    @Modifying
    @Transactional
    @Query("update Album set tag = :tag where id = :id")
    int tagAlbum(@Param(value = "id") String id, @Param(value = "tag") String tag);
}
