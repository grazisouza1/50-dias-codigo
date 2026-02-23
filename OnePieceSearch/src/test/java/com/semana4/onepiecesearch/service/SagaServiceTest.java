package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.SagaDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;


public class SagaServiceTest {
    @Test
    public void retornaTodosOsAtributos () throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        SagaService service = new SagaService(restTemplate);

        SagaDto resultado = service.searchSagaById(1);

        assertEquals("1 à 100", resultado.getSaga_chapitre());
        assertEquals("1 à 61", resultado.getSaga_episode());
        assertEquals("1 à 12", resultado.getSaga_volume());
        assertEquals("East Blue", resultado.getTitle());
    }
}
