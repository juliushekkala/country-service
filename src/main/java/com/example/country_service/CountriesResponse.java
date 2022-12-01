package com.example.country_service;

import java.util.List;

/**
 * Response when the user asks for all the countries.
 */
public class CountriesResponse {

    private List<Country> countries;

    public CountriesResponse(List<Country> countries) {
        this.countries = countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

}

