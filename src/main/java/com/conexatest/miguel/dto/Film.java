package com.conexatest.miguel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Film {

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "episode_id")
    private String episodeId;

    @JsonProperty(value = "opening_crawl")
    private String openingCrawl;

    @JsonProperty(value = "director")
    private String director;

    @JsonProperty(value = "producer")
    private String producer;

    @JsonProperty(value = "release_date")
    private String releaseDate;

    @JsonProperty(value = "characters")
    private String[] characters;

    @JsonProperty(value = "planets")
    private String[] planets;

    @JsonProperty(value = "starships")
    private String[] starships;

    @JsonProperty(value = "vehicles")
    private String[] vehicles;

    @JsonProperty(value = "species")
    private String[] species;

    @JsonProperty(value = "created")
    private String created;

    @JsonProperty(value = "edited")
    private String edited;

    @JsonProperty(value = "url")
    private String url;

}
