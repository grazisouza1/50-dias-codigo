package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CharacterDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterServiceTest {

    @Test
    public void retornaTodosOsAtributos () throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        CharacterService service = new CharacterService(restTemplate);

        CharacterDto resultado = service.searchByName("Roronoa Zoro");

        assertEquals("Right-hand man", resultado.getJob());
        assertEquals("320.000.000", resultado.getBounty());
        assertEquals("Mugiwara no Ichimi", resultado.getCrew().getRoman_name());
    }
}
