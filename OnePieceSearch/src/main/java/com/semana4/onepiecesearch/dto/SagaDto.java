package com.semana4.onepiecesearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SagaDto {
    String title;
    String saga_chapitre;
    String saga_volume;
    String saga_episode;

    public String getTitle() {
        return title;
    }

    public String getSaga_chapitre() {
        return saga_chapitre;
    }

    public String getSaga_volume() {
        return saga_volume;
    }

    public String getSaga_episode() {
        return saga_episode;
    }
}
