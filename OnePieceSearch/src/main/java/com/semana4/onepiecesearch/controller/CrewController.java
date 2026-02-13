package com.semana4.onepiecesearch.controller;

import com.semana4.onepiecesearch.service.CrewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/crews/en")
public class CrewController {
    private final CrewService service;

    public CrewController(CrewService service){
        this.service = service;
    }
}
