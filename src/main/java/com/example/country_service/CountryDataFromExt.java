package com.example.country_service;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class is used to get country specific data from REST Countries API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDataFromExt {
    @JsonProperty("name")
    private Name name;

    @JsonProperty("cca2")
    private String countryCode;

    @JsonProperty("capital")
    private List<String> capital;

    @JsonProperty("population")
    private int population;

    @JsonProperty("flags")
    private FlagFile flagFile;

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return this.name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

   public void setCapital(List<String> capital) {
        this.capital = capital;
   }

   public List<String> getCapital() {
        return capital;
   }

   public void setPopulation(int population) {
        this.population = population;
   }

   public int getPopulation() {
        return population;
   }

   public void setFlagFile(FlagFile flagFile) {
        this.flagFile = flagFile;
    }

   public FlagFile getFlagFile() {
        return flagFile;
    }

   //For easier accessing of data
   public String getCountryName() {
        return name.getCountryName();
   }

   public String getFlagFileUrl() {
        return flagFile.getFlagFileUrl();
   }


    public static class Name {
        @JsonProperty("common")
        private String countryName;

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCountryName() {
            return countryName;
        }
    }

    public static class FlagFile {
        @JsonProperty("png")
        private String flagFileUrl;

        public void setFlagFileUrl(String flagFileUrl) {
            this.flagFileUrl = flagFileUrl;
        }

        public String getFlagFileUrl() {
            return flagFileUrl;
        }
    }

}
