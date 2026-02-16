package com.semana4.onepiecesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.SagaDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Service
public class SagaService {
    ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate;

    public SagaService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public SagaDto searchSagaById(int sagaId) throws JsonProcessingException {
        String url = "https://api.api-onepiece.com/v2/sagas/en/" + sagaId;

        String json = restTemplate.getForObject(url, String.class);

        return mapper.readValue(json, SagaDto.class);
    }

    public SagaDto searchByName(String sagaName) throws JsonProcessingException {
            String url = "https://api.api-onepiece.com/v2/sagas/en/search?title=" + sagaName;

            String json = restTemplate.getForObject(url, String.class);

            List<SagaDto> sagas = mapper.readValue(json, new TypeReference<List<SagaDto>>() {});

            if (sagas.isEmpty()) return null;

            return sagas.getFirst();
    }
}
