package kz.abylay.example.services;

import kz.abylay.example.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountry();

    Country getCountryById(Integer id);
}
