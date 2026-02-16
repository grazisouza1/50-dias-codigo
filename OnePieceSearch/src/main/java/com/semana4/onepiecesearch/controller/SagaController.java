package com.semana4.onepiecesearch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.SagaDto;
import com.semana4.onepiecesearch.service.SagaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/sagas/en")
public class SagaController {
    private final SagaService sagaService;

    public SagaController(SagaService sagaService) {
        this.sagaService = sagaService;
    }

    @GetMapping("/{id}")
    public SagaDto searchById(@PathVariable int sagaId) throws JsonProcessingException {
        return sagaService.searchSagaById(sagaId);
    }

    @GetMapping("/search")
    public SagaDto searchByName(@RequestParam String sagaName) throws JsonProcessingException {
        return sagaService.searchByName(sagaName);
    }
}
