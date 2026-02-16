package com.semana4.onepiecesearch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CrewDto;
import com.semana4.onepiecesearch.service.CrewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/episodes/en")
public class EpisodeController {
    private final CrewService crewService;

    public EpisodeController(CrewService crewService){
        this.crewService = crewService;
    }

    @GetMapping("/{id}")
    public CrewDto search (@PathVariable int episodeId) throws JsonProcessingException {
        return crewService.searchCrewById(episodeId);
    }
}
