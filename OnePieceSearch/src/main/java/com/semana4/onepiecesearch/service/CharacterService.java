package com.semana4.onepiecesearch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterService {

    private final RestTemplate restTemplate;

    public CharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchByName(String name) {
        String url = "https://api.api-onepiece.com/v2/characters/en/search?name=" + name;
        return restTemplate.getForObject(url, String.class);
    }
}
