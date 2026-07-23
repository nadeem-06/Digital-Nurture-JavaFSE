package com.cognizant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.Country;
import com.cognizant.service.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // ─────────────────────────────────────
    // GET /api/countries
    // Get all countries
    // ─────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries =
            countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    // ─────────────────────────────────────
    // GET /api/countries/{id}
    // Get country by ID
    // ─────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(
            @PathVariable int id) {
        Country country =
            countryService.getCountryById(id);
        return ResponseEntity.ok(country);
    }

    // ─────────────────────────────────────
    // GET /api/countries/code/{code}
    // Get country by country code
    // ─────────────────────────────────────
    @GetMapping("/code/{code}")
    public ResponseEntity<Country> getCountryByCode(
            @PathVariable String code) {
        Country country =
            countryService.getCountryByCode(code);
        return ResponseEntity.ok(country);
    }

    // ─────────────────────────────────────
    // POST /api/countries
    // Add new country
    // ─────────────────────────────────────
    @PostMapping
    public ResponseEntity<Country> addCountry(
            @RequestBody Country country) {
        Country saved =
            countryService.addCountry(country);
        return new ResponseEntity<>(
            saved, HttpStatus.CREATED
        );
    }

    // ─────────────────────────────────────
    // PUT /api/countries/{id}
    // Update country
    // ─────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(
            @PathVariable int id,
            @RequestBody Country country) {
        Country updated =
            countryService.updateCountry(id, country);
        return ResponseEntity.ok(updated);
    }

    // ─────────────────────────────────────
    // DELETE /api/countries/{id}
    // Delete country
    // ─────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>>
            deleteCountry(@PathVariable int id) {
        String message =
            countryService.deleteCountry(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────
    // GET /api/countries/count
    // Get total count
    // ─────────────────────────────────────
    @GetMapping("/count")
    public ResponseEntity<Map<String, Integer>>
            getCount() {
        Map<String, Integer> response = new HashMap<>();
        response.put("totalCountries",
            countryService.getTotalCount());
        return ResponseEntity.ok(response);
    }
}