package com.semana4.onepiecesearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "age", "size", "bounty", "job", "crew" })
@JsonIgnoreProperties(ignoreUnknown = true)

public class CharacterDto {
    String bounty;
    String name;
    CrewDto crew;
    String job;

    public String getBounty() {
        return bounty;
    }

    public String getName() {
        return name;
    }

    public CrewDto getCrew() {
        return crew;
    }

    public String getJob() {
        return job;
    }
}
