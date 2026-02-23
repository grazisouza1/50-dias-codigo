package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semana4.onepiecesearch.dto.CrewDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrewService {
    private final RestTemplate restTemplate;

    public CrewService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public CrewDto searchCrewById(int crewId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://api.api-onepiece.com/v2/crews/en/" + crewId;

        // Executa uma requisição HTTP GET e obtém a resposta como String
        String json = restTemplate.getForObject(url, String.class);

        // Converte o JSON retornado em um objeto do tipo CrewDto e o retorna
        return mapper.readValue(json, CrewDto.class);
    }
}
