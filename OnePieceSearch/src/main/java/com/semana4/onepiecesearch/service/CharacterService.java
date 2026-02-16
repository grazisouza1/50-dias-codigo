package com.semana4.onepiecesearch.service;

import com.semana4.onepiecesearch.dto.CharacterDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CharacterService {

    private final RestTemplate restTemplate;

    public CharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CharacterDto searchByName(String name) {
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://api.api-onepiece.com/v2/characters/en/search?name=" + name;

        String json = restTemplate.getForObject(url, String.class);

        List<CharacterDto> characters = mapper.readValue(json, new TypeReference<List<CharacterDto>>() {});

        if (characters.isEmpty()){
            return null;
        }

        return characters.getFirst();
    }
}
