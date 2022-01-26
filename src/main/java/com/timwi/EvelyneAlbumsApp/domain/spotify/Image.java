package com.timwi.EvelyneAlbumsApp.domain.spotify;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
    Integer height;
    String url;
    Integer width;
}
