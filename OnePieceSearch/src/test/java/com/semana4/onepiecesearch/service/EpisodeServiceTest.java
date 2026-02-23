package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.EpisodeDto;
import com.semana4.onepiecesearch.dto.SagaDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class EpisodeServiceTest {
    @Test
    public void retornaTodosOsAtributos () throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        EpisodeService service = new EpisodeService(restTemplate);

        SagaService sagaService = new SagaService(restTemplate);
        SagaDto saga = sagaService.searchSagaById(1);
        EpisodeDto resultado = service.searchEpisodeByNumber(1);

        assertEquals("Chap 2", resultado.getChapter());
        assertEquals("I'm Luffy! The one who will become King of the Pirates!", resultado.getTitle());
        assertEquals(1, resultado.getId());
        assertEquals(saga, resultado.getSaga());
        assertEquals("1999-10-20", resultado.getRelease_date());
    }
}
