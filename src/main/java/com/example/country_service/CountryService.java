package com.example.country_service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class gets countries from REST countries API https://restcountries.com/.
 */
@Service
public class CountryService {
    private final RestTemplate restTemplate;

    public CountryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**Get all countries (name and country code).
     * Returns the response to send to the client.
     **/
    public CountriesResponse getAllCountries() {
         //Get the countries from REST countries API, filter for related fields
        String url = "https://restcountries.com/v3/all?fields=name,cca2";
        try {
            ResponseEntity<List<CountryDataFromExt>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CountryDataFromExt>>() {
            });
            //Create response object
            List<CountryDataFromExt> countriesData = response.getBody();
            List<Country> responseCountries = new ArrayList<>();
            for (CountryDataFromExt country : countriesData) {
                responseCountries.add(new Country(country.getCountryName(), country.getCountryCode()));
            }
            CountriesResponse countriesResponse = new CountriesResponse(responseCountries);
            return countriesResponse;
        } catch (RestClientException | NullPointerException e) {
            //Return JSON with empty list "countries" if something went wrong
            List<Country> responseCountries = new ArrayList<>();
            CountriesResponse countriesResponse = new CountriesResponse(responseCountries);
            return countriesResponse;
        }
    };

    /**
     * Get data for a single country. Searches the REST Countries API with the given name.
     * Returns a single country, or throws an exception.
     */
    public Country getCountry(String name) throws CountryServiceException {
        //Search the country from REST countries API, filter for related fields
        String url = "https://restcountries.com/v3.1/name/" + name.toLowerCase() + "?fields=name,cca2,capital,population,flags";
        try {
            ResponseEntity<List<CountryDataFromExt>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CountryDataFromExt>>() {
            });
            //Create response object
            List<CountryDataFromExt> countryDataFromExt = response.getBody();
            //There might be multiple results if the user uses a short search string. Always takes the first one.
            CountryDataFromExt countryData = countryDataFromExt.get(0);
            Country country = new Country(countryData.getCountryName(), countryData.getCountryCode(), countryData.getCapital().get(0), countryData.getPopulation(), countryData.getFlagFileUrl());
            return country;
            //Catch exception when getting data from REST countries API
        } catch (RestClientException | NullPointerException e) {
            throw new CountryServiceException(e.getMessage());
        }

    }


}
