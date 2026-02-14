package com.semana4.onepiecesearch.controller;

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
    public String searchById(@PathVariable int sagaId) {
        return sagaService.searchSagaById(sagaId);
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam String sagaName){
        return sagaService.searchByName(sagaName);
    }
}
