package com.semana4.onepiecesearch.dto;

public class CharacterDto {
    int id;
    String bounty;
    String name;
    FruitDto fruit;
    CrewDto crew;
    String job;

    public String getBounty() {
        return bounty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FruitDto getFruit() {
        return fruit;
    }

    public CrewDto getCrew() {
        return crew;
    }

    public String getJob() {
        return job;
    }
}
