package com.semana4.onepiecesearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SagaDto {
    String title;
    String saga_chapitre;
    String saga_volume;
    String saga_episode;

}
