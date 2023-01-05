package com.conexatest.miguel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class People {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "height")
    private String height;

    @JsonProperty(value = "mass")
    private String mass;

    @JsonProperty(value = "hair_color")
    private String hairColor;

    @JsonProperty(value = "skin_color")
    private String skinColor;

    @JsonProperty(value = "eye_color")
    private String eyeColor;

    @JsonProperty(value = "birth_year")
    private String birthYear;

    @JsonProperty(value = "gender")
    private String gender;

    @JsonProperty(value = "homeworld")
    private String homeworld;

    @JsonProperty(value = "films")
    private String[] films;

    @JsonProperty(value = "species")
    private String[] species;

    @JsonProperty(value = "vehicles")
    private String[] vehicles;

    @JsonProperty(value = "starships")
    private String[] starships;

    @JsonProperty(value = "created")
    private String created;

    @JsonProperty(value = "edited")
    private String edited;

    @JsonProperty(value = "url")
    private String url;




}
