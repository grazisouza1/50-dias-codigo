package com.semana4.onepiecesearch.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CharacterDto;
import com.semana4.onepiecesearch.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;


public class MenuTest {

    @Test
    void deveRetornarZoroMesmoComDoisNomes() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        CharacterService service = new CharacterService(restTemplate);

        CharacterDto resultado = service.searchByName("Roronoa Zoro");

        assertEquals("Roronoa Zoro", resultado.getName());
    }

    @Test
    void deveRetornarLuffyMesmoComMaisDeUmNome() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        CharacterService service = new CharacterService(restTemplate);

        CharacterDto resultado = service.searchByName("Monkey D Luffy");

        assertEquals("Monkey D Luffy", resultado.getName());
    }
}