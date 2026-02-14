package com.semana4.onepiecesearch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class FruitService {
    private final RestTemplate restTemplate;

    public FruitService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //public String searchByType(@RequestParam String fruitType) {
    //}
}
