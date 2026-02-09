package com.semana3.apicaller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotDto {

    //Criando as informações que serão usadas sobre cada piloto
    int session_key;
    String full_name;
    String name_acronym;
    String team_name;
    String team_colour;
    String country_code ;


    public int getSession_key() {
        return session_key;
    }

    public void setSession_key(int session_key) {
        this.session_key = session_key;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getName_acronym() {
        return name_acronym;
    }

    public void setName_acronym(String name_acronym) {
        this.name_acronym = name_acronym;
    }

    public String getTeam_colour() {
        return team_colour;
    }

    public void setTeam_colour(String team_colour) {
        this.team_colour = team_colour;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "PilotDto{" +
                "\nfull_name='" + full_name + '\'' +
                "\nname_acronym='" + name_acronym + '\'' +
                "\nteam_name='" + team_name + '\'' +
                "\nteam_colour='" + team_colour + '\'' +
                "\ncountry_code='" + country_code + '\'' +
                "\n}";
    }
}
