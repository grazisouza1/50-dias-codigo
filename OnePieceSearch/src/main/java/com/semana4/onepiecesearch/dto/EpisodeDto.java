package com.semana4.onepiecesearch.dto;

public class EpisodeDto {
    int id;
    String title;
    String description;
    String chapter;
    String release_date;
    SagaDto saga;
    // ArcDto arc;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getChapter() {
        return chapter;
    }

    public String getRelease_date() {
        return release_date;
    }

    public SagaDto getSaga() {
        return saga;
    }
}
