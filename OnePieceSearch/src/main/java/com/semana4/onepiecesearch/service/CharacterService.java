package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CharacterDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Service
public class CharacterService {
    ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate;

    public CharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CharacterDto searchByName(String name) throws JsonProcessingException {
        String url = "https://api.api-onepiece.com/v2/characters/en/search?name=" + name;

        String json = restTemplate.getForObject(url, String.class);

        List<CharacterDto> characters = mapper.readValue(json, new TypeReference<List<CharacterDto>>() {});

        if (characters.isEmpty()){
            return null;
        }

        return characters.getFirst();
    }
}
