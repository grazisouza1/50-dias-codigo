package com.semana4.onepiecesearch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana4.onepiecesearch.dto.CharacterDto;
import com.semana4.onepiecesearch.service.CharacterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/characters/en")
public class CharacterController {
    private final CharacterService service;

    public CharacterController(CharacterService service){
        this.service = service;
    }

    @GetMapping("/search")
    public CharacterDto search(@RequestParam String name) throws JsonProcessingException {
        return service.searchByName(name);
    }

}
