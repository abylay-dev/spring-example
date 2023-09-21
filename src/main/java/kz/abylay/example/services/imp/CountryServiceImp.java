package kz.abylay.example.services.imp;

import kz.abylay.example.model.Country;
import kz.abylay.example.repository.CountryRepository;
import kz.abylay.example.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImp implements CountryService {
    @Autowired
    private CountryRepository countryRepository;


    @Override
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }
}