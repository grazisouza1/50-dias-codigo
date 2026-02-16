package com.semana4.onepiecesearch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CrewDto;
import com.semana4.onepiecesearch.service.CrewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/crews/en")
public class CrewController {
    private final CrewService service;

    public CrewController(CrewService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public CrewDto search(@PathVariable int crewId) throws JsonProcessingException {
        return service.searchCrewById(crewId);
    }
}
