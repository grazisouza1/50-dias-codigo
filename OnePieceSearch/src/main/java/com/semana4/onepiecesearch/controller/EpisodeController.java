package com.semana4.onepiecesearch.controller;

import com.semana4.onepiecesearch.service.CrewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v2/episodes/en")
public class EpisodeController {
    private final CrewService crewService;


    public EpisodeController(CrewService crewService){
        this.crewService = crewService;
    }

    @GetMapping("/{id}")
    public String search (@PathVariable int episodeId) {
        return crewService.searchCrewById(episodeId);
    }
}
