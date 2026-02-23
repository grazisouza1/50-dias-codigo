package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CrewDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class CrewServiceTest {
    @Test
    public void retornaTodosOsAtributos () throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        CrewService service = new CrewService(restTemplate);

        CrewDto resultado = service.searchCrewById(1);

        assertEquals("Mugiwara no Ichimi", resultado.getRoman_name());
        assertEquals("3.161.000.100", resultado.getTotal_prime());
        assertEquals("The Chapeau de Paille crew", resultado.getName());
        assertEquals("10", resultado.getNumber());
        assertEquals("assets", resultado.getStatus());
    }

}
