package com.semana4.onepiecesearch.service;

import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrewService {
    private final RestTemplate restTemplate;

    public CrewService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String searchCrewById(int crewId) {
        String url = "https://api.api-onepiece.com/v2/crews/en/" + crewId;
        return restTemplate.getForObject(url, String.class);
    }
}
