package com.semana3.apicaller.service;

//Efetua a requisição para a API

import com.semana3.apicaller.dto.PilotDto;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class APIService {
    public String getPilotName(String firstName) {

        PilotDto pilotDto = new PilotDto();

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openf1.org/v1/drivers?first_name=" + firstName)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            List<PilotDto> pilots = mapper.readValue(response.body(), new TypeReference<List<PilotDto>>() {
            });

            if(pilots.isEmpty()) {
                return null;
            }

            return pilots.getFirst().toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PilotDto getPilotSession(int session) {

        PilotDto pilotDto = new PilotDto();

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openf1.org/v1/drivers?session_key=" + session)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            pilotDto = mapper.readValue(response.body(), PilotDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        return pilotDto;
    }
}
