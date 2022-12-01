package com.example.country_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    CountryService service;

    /**
     * Get all countries. Returns all countries in JSON format.
     */
    @GetMapping("/")
    public @ResponseBody CountriesResponse getCountries() {
        return service.getAllCountries();
    }

    /**
     * Get data about a specific country. Returns the data or 404 if the country is not found.
     */
    @GetMapping("/{name}")
    public @ResponseBody Country getCountry(@PathVariable String name) throws ResponseStatusException {
        try {
            Country country =  service.getCountry(name);
            return country;
        } catch (CountryServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country was not found");
        }
    }
}
