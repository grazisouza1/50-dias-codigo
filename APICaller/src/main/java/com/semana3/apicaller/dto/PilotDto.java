package com.semana3.apicaller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotDto {

    int driver_number;
    String full_name;
    String name_acronym;
    String team_name;
    String team_colour;
    String headshot_url;
    String country_code ;

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getDriver_number() {
        return driver_number;
    }

    public void setDriver_number(int driver_number) {
        this.driver_number = driver_number;
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

    public String getHeadshot_url() {
        return headshot_url;
    }

    public void setHeadshot_url(String headshot_url) {
        this.headshot_url = headshot_url;
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
                "\ndriver_number=" + driver_number +
                "\nfull_name='" + full_name + '\'' +
                "\nname_acronym='" + name_acronym + '\'' +
                "\nteam_name='" + team_name + '\'' +
                "\nteam_colour='" + team_colour + '\'' +
                "\nheadshot_url='" + headshot_url + '\'' +
                "\ncountry_code='" + country_code + '\'' +
                "\n}";
    }
}
