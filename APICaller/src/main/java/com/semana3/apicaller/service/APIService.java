package com.semana3.apicaller.service;

//Efetua a requisição para a API

import com.semana3.apicaller.dto.PilotDto;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class APIService {
    public List<PilotDto> requestPilots(String query) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openf1.org/v1/drivers?" + query)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), new TypeReference<List<PilotDto>>() {});

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<PilotDto> getPilotsByCountry(String country) {
        return requestPilots("country_code=" + country.trim().toUpperCase());
    }

    public List<PilotDto> getPilotsByTeam(String team) {
        return requestPilots("team_name=" + team.trim().replace(" ","%20" ));
    }

    public List<PilotDto> getPilotsByFirstName(String name) {
        return requestPilots("first_name=" + name.trim());
    }
}
