package com.semana4.onepiecesearch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EpisodeService {
    private final RestTemplate restTemplate;

    public EpisodeService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchEpisodeByNumber(int episodeNumber) {
        String url = "https://api.api-onepiece.com/v2/episodes/en/" + episodeNumber;
        return restTemplate.getForObject(url, String.class);
    }
}
