package com.example.country_service;

/**
 * Custom exception class for CountryService.
 */
public class CountryServiceException extends Exception {
    public CountryServiceException(String errorMessage) {
        super(errorMessage);
    }
}
