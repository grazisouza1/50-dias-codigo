package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semana4.onepiecesearch.dto.EpisodeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EpisodeService {
    ObjectMapper mapper =new ObjectMapper();
    private final RestTemplate restTemplate;

    public EpisodeService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EpisodeDto searchEpisodeByNumber(int episodeNumber) throws JsonProcessingException {
        String url = "https://api.api-onepiece.com/v2/episodes/en/" + episodeNumber;

        String json = restTemplate.getForObject(url, String.class);

        return mapper.readValue(json, EpisodeDto.class);
    }
}
