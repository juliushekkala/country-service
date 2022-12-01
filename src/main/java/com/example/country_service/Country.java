package com.example.country_service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Country object used in the responses.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({"name", "country_code", "capital", "population", "flag_file_url"})
public class Country {
    //Always present fields
    @JsonProperty("name")
    private String name;
    @JsonProperty("country_code")
    private String countryCode;

    //fields present when getting a single country
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("population")
    private int population;
    @JsonProperty("flag_file_url")
    private String flagFileUrl;

    public Country(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public Country(String name, String countryCode, String capital, int population, String flagFileUrl) {
        this.name = name;
        this.countryCode = countryCode;
        this.capital = capital;
        this.population = population;
        this.flagFileUrl = flagFileUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
       return countryCode;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setFlagFileUrl(String flagFileUrl) {
        this.flagFileUrl = flagFileUrl;
    }

    public String getFlagFileUrl() {
        return flagFileUrl;
    }

}
