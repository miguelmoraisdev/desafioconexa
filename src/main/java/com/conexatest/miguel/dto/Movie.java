package com.conexatest.miguel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

    private String title;
    private String episodeId;
    private String director;
    private String releaseDate;

}
