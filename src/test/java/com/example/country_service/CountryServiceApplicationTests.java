package com.example.country_service;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	//Autowired
	///private Country country;

	@Test
	/**
	 * Test all countries endpoint.
	 * Tests that endpoint returns 200 and it contains a countries list with the correct first fields types.
	 */
	public void getAllCountries() throws Exception {
			mvc.perform(MockMvcRequestBuilders.get("/countries/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.countries", notNullValue()))
				.andExpect(jsonPath("$.countries[0].name", instanceOf(String.class)))
				.andExpect(jsonPath("$.countries[0].country_code", instanceOf(String.class)));
	}

	@Test
	/**
	 * Test a single country endpoint with a real country (Finland).
	 * Tests that the endpoint returns 200 and content that is correct.
	 */
	public void getSingleCorrectCountry() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/countries/finland").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Finland")))
				.andExpect(jsonPath("$.country_code", equalTo("FI")))
				.andExpect(jsonPath("$.capital", equalTo("Helsinki")))
				.andExpect(jsonPath("$.population", instanceOf(int.class)))
				.andExpect(jsonPath("$.flag_file_url", instanceOf(String.class)));
	}


	@Test
	/**
	 * Test a single country endpoint with a non-real country (Finlandsuomi).
	 * Tests that the endpoint returns 404 and no content.
	 */
	public void getSingleIncorrectCountry() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/countries/finlandsuomi").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	/**
	 * Test a single country endpoint with a short string that will find some random country
	 * Tests that endpoint returns 200 and content that is not null.
	 */
	public void getSingleCorrectCountryWithShortString() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/countries/nl").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", instanceOf(String.class)))
				.andExpect(jsonPath("$.country_code", instanceOf(String.class)))
				.andExpect(jsonPath("$.capital", instanceOf(String.class)))
				.andExpect(jsonPath("$.population", instanceOf(int.class)))
				.andExpect(jsonPath("$.flag_file_url", instanceOf(String.class)));
	}

	@Test
	/**
	 * Test a single country endpoint with a real country but with changing letter sizes (FiNLaND).
	 * Tests that the endpoint returns 200 and content that is correct.
	 */
	public void getSingleCorrectCountryWithChangingLetterSizes() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/countries/FiNLaND").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Finland")))
				.andExpect(jsonPath("$.country_code", equalTo("FI")))
				.andExpect(jsonPath("$.capital", equalTo("Helsinki")))
				.andExpect(jsonPath("$.population", instanceOf(int.class)))
				.andExpect(jsonPath("$.flag_file_url", instanceOf(String.class)));
	}

	@Test
	/**
	 * Test the Country object used for responses
	 */
	public void testCountryObject() {
		Country country = new Country("CountryName", "CO");
		assertThat(country.getName()).isEqualTo("CountryName");
		assertThat(country.getCountryCode()).isEqualTo("CO");
		country.setCapital("CountryCapital");
		country.setPopulation(200);
		country.setFlagFileUrl("url");
		assertThat(country.getCapital()).isEqualTo("CountryCapital");
		assertThat(country.getPopulation()).isEqualTo(200);
		assertThat(country.getFlagFileUrl()).isEqualTo("url");
		country.setName("NewCountryName");
		country.setCountryCode("NE");
		assertThat(country.getName()).isEqualTo("NewCountryName");
		assertThat(country.getCountryCode()).isEqualTo("NE");

		Country anotherCountry = new Country("AnotherCountry", "AN", "Capital", 500, "url");
		assertThat(anotherCountry.getName()).isEqualTo("AnotherCountry");
		assertThat(anotherCountry.getCountryCode()).isEqualTo("AN");
		assertThat(anotherCountry.getCapital()).isEqualTo("Capital");
		assertThat(anotherCountry.getPopulation()).isEqualTo(500);
		assertThat(anotherCountry.getFlagFileUrl()).isEqualTo("url");
	}

	@Test
	/**
	 * Test the countries response
	 */
	public void testCountriesResponse() {
		Country country = new Country("CountryName", "CO");
		Country anotherCountry = new Country("AnotherCountry", "AN");
		CountriesResponse countriesResponse = new CountriesResponse(List.of(country, anotherCountry));
		assertThat(countriesResponse.getCountries()).isEqualTo(List.of(country, anotherCountry));
		Country thirdCountry = new Country("Name", "NA");
		countriesResponse.setCountries(List.of(thirdCountry));
		assertThat(countriesResponse.getCountries()).isEqualTo(List.of(thirdCountry));
	}

	@Test
	/**
	 * Test the object that is used to collect data from REST Countries API
	 */
	public void testCountryDataFromExtObject() {
		CountryDataFromExt countryData = new CountryDataFromExt();
		CountryDataFromExt.Name countryName = new CountryDataFromExt.Name();
		countryName.setCountryName("CountryName");
		assertThat(countryName.getCountryName()).isEqualTo("CountryName");
		countryData.setName(countryName);
		assertThat(countryData.getCountryName()).isEqualTo("CountryName");
		assertThat(countryData.getName()).isEqualTo(countryName);
		countryData.setCountryCode("CO");
		assertThat(countryData.getCountryCode()).isEqualTo("CO");
		countryData.setCapital(List.of("Capital"));
		assertThat(countryData.getCapital()).isEqualTo(List.of("Capital"));
		countryData.setPopulation(200);
		assertThat(countryData.getPopulation()).isEqualTo(200);
		CountryDataFromExt.FlagFile flagFile = new CountryDataFromExt.FlagFile();
		flagFile.setFlagFileUrl("url");
		assertThat(flagFile.getFlagFileUrl()).isEqualTo("url");
		countryData.setFlagFile(flagFile);
		assertThat(countryData.getFlagFileUrl()).isEqualTo("url");
		assertThat(countryData.getFlagFile()).isEqualTo(flagFile);
	}
}