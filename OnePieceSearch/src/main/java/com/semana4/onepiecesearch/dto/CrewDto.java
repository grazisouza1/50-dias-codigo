package com.semana4.onepiecesearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewDto {
    String name;
    String status;
    String roman_name;
    String total_prime;
    String number;

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getRoman_name() {
        return roman_name;
    }

    public String getTotal_prime() {
        return total_prime;
    }
}
