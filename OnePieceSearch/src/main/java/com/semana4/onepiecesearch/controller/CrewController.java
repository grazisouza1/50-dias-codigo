package com.semana4.onepiecesearch.controller;

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
    public String search(@PathVariable int crewId) {
        return service.searchCrewById(crewId);
    }
}
