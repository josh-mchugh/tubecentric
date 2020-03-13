package com.tubecentric.webapplication.youtube.thumbnail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Thumbnails {

    @JsonProperty("default")
    private Default _default;

    @JsonProperty("medium")
    private Medium medium;

    @JsonProperty("high")
    private High high;

    @JsonProperty("standard")
    private Standard standard;

    @JsonProperty("maxres")
    private Max maxres;
}