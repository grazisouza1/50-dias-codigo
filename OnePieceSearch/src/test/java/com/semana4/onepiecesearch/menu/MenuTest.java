package com.semana4.onepiecesearch.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CharacterDto;
import com.semana4.onepiecesearch.service.CharacterService;
import com.semana4.onepiecesearch.service.CrewService;
import com.semana4.onepiecesearch.service.EpisodeService;
import com.semana4.onepiecesearch.service.SagaService;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;


public class MenuTest {

    @Test
    public void entradaDoUserNaoPodeSerVazia() {
        Menu menu = new Menu(null, null, null, null);

        Integer resultado = menu.processInput("");

        assertNull(resultado);
    }

    @Test
    public void deveRetornarNumeroValido(){
        Menu menu = new Menu(null, null, null, null);

        Integer resultado = menu.processInput("2");

        assertEquals("2", resultado.toString());
    }
}