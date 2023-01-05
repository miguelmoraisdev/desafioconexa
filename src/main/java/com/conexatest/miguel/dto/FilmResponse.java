package com.conexatest.miguel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FilmResponse {

    private String title;

    @JsonProperty(value = "episode_id")
    private String episodeId;
    private String director;

    @JsonProperty(value = "release_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date releaseDate;

}
