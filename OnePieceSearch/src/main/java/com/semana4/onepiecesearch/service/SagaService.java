package com.semana4.onepiecesearch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SagaService {
    private final RestTemplate restTemplate;

    public SagaService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String searchSagaById(int sagaId){
        String url = "https://api.api-onepiece.com/v2/sagas/en/" + sagaId;
        return restTemplate.getForObject(url, String.class);
    }

    public String searchByName(String sagaName) {
        String url = "https://api.api-onepiece.com/v2/sagas/en/search?title=" + sagaName;
        return  restTemplate.getForObject(url, String.class);
    }
}
