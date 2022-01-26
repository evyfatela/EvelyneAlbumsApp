package com.timwi.EvelyneAlbumsApp.controller;

import com.timwi.EvelyneAlbumsApp.domain.dto.AlbumDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Evelyne Album App", description = "Timwi coding challenge", version = "1.0",
        contact = @Contact(name = "Evelyne Fatela Nabais", email = "evyfatela@hotmail.fr")))
public interface EvelyneAlbumsAppController {
    //TODO logs
    //TODO security
    //TODO tests

    @Operation(summary = "Search album by artist or album name",
            description = "Search albums by an artist name and/or an album name.\n\n" +
                          "It relies on Spotify search API.\n\n" +
                          "We return only the 20 first results.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of albums based on the given criteria",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AlbumDto.class)))),
            @ApiResponse(responseCode = "204", description = "No albums found based on the given criteria",
                    content = @Content(schema = @Schema()))})
    ResponseEntity<List<AlbumDto>> searchAlbums(String artist, String album);

    @Operation(summary = "Save album",
            description = "Save an album in the app's library")
    @ApiResponses(value =
    @ApiResponse(responseCode = "201", description = "Album saved", content = @Content(schema = @Schema())))
    ResponseEntity<Void> saveAlbum(AlbumDto albumDto);

    @Operation(summary = "Get album",
            description = "Retrieve an album from the app's library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Album from the app's library",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AlbumDto.class))),
            @ApiResponse(responseCode = "404", description = "Album not found",
                    content = @Content(schema = @Schema()))})
    ResponseEntity<AlbumDto> getAlbum(String id);

    @Operation(summary = "Mark or unmark album as favorite",
            description = "Mark an album as favorite or unmark it if it's a favorite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marked or unmarked as favorite",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Album not found",
                    content = @Content(schema = @Schema()))})
    ResponseEntity<Void> markUnmarkFavorite(String id);

    @Operation(summary = "Tag album",
            description = "Add the given tag to an album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag added", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Album not found",
                    content = @Content(schema = @Schema()))})
    ResponseEntity<Void> tagAlbum(String id, String tag);

    @Operation(summary = "Delete album",
            description = "Delete an album from the app's library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Album deleted", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Album not found",
                    content = @Content(schema = @Schema()))})
    ResponseEntity<Void> deleteAlbum(String id);
}
