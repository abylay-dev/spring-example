package kz.abylay.example.controllers;

import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Country;
import kz.abylay.example.services.CarsService;
import kz.abylay.example.services.CountryService;
import kz.abylay.example.services.MarketplaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/update-country-page/{id}")
    public String getUpdateCountriesPage(@PathVariable("id") Integer id, Model model){
        Country c = countryService.getCountryById(id);
        model.addAttribute("countries", c);
        if (countryService.getCountryById(++id) != null) {
            model.addAttribute("next_country_id", id);
        }
        return "editCountries";
    }

    @PostMapping("/update-country")
    public String updateCountries(@RequestParam("id") Integer id,
                             @RequestParam("name") String name){
        Country c = new Country(id, name);
        countryService.updateCountry(c);
        return "redirect:/update-country-page/" + id;
    }

    @GetMapping("/delete-country")
    public String deleteCountriesById(@RequestParam("countriesId") Integer id){
        countryService.deleteCountry(id);
        return "redirect:/admin-panel";
    }

    @GetMapping("/add-country-page")
    public String getAddCountriesPage(@RequestParam(value = "fromAdmin", defaultValue = "false") Boolean fromAdmin, Model model) {
        model.addAttribute("countries", countryService.getAllCountry());
        if (fromAdmin){
            model.addAttribute("carsBackToPage", "/admin-panel");
        } else {
            model.addAttribute("carsBackToPage", "/table-cars");
        }
        return "addCountry";
    }

    @PostMapping("/add-country")
    public String addCountries(@RequestParam("name") String name) {
        countryService.addCountry(new Country(null, name));
        return "redirect:/admin-panel";
    }

}
