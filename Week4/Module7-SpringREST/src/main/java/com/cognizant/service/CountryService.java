package com.cognizant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.model.Country;

@Service
public class CountryService {

    // In-memory list acting as database
    private List<Country> countries = new ArrayList<>();
    private int idCounter = 1;

    // Constructor - load sample data
    public CountryService() {
        countries.add(new Country(
            idCounter++, "IN", "India",
            "New Delhi", "INR", 1400000000L
        ));
        countries.add(new Country(
            idCounter++, "US", "United States",
            "Washington D.C.", "USD", 331000000L
        ));
        countries.add(new Country(
            idCounter++, "GB", "United Kingdom",
            "London", "GBP", 67000000L
        ));
        countries.add(new Country(
            idCounter++, "JP", "Japan",
            "Tokyo", "JPY", 125000000L
        ));
        countries.add(new Country(
            idCounter++, "AU", "Australia",
            "Canberra", "AUD", 26000000L
        ));
    }

    // GET ALL
    public List<Country> getAllCountries() {
        return countries;
    }

    // GET BY ID
    public Country getCountryById(int id) {
        return countries.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                    new RuntimeException(
                        "Country not found with id: " + id
                    )
                );
    }

    // GET BY CODE
    public Country getCountryByCode(String code) {
        return countries.stream()
                .filter(c -> c.getCountryCode()
                    .equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                    new RuntimeException(
                        "Country not found with code: " + code
                    )
                );
    }

    // ADD COUNTRY
    public Country addCountry(Country country) {
        country.setId(idCounter++);
        countries.add(country);
        return country;
    }

    // UPDATE COUNTRY
    public Country updateCountry(int id,
                                 Country updated) {
        Country existing = getCountryById(id);
        existing.setCountryCode(updated.getCountryCode());
        existing.setCountryName(updated.getCountryName());
        existing.setCapital(updated.getCapital());
        existing.setCurrency(updated.getCurrency());
        existing.setPopulation(updated.getPopulation());
        return existing;
    }

    // DELETE COUNTRY
    public String deleteCountry(int id) {
        Country country = getCountryById(id);
        countries.remove(country);
        return "Country deleted: " +
               country.getCountryName();
    }

    // GET TOTAL COUNT
    public int getTotalCount() {
        return countries.size();
    }
}