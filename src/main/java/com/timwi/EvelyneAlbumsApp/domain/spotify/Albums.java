package com.timwi.EvelyneAlbumsApp.domain.spotify;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Albums {
    String href;
    List<Album> items = new ArrayList<>();
    Integer limit;
    String next;
    Integer offset;
    String previous;
    Integer total;
}
