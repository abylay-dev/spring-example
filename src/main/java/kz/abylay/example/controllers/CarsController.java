package kz.abylay.example.controllers;

import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Country;
import kz.abylay.example.services.CarsService;
import kz.abylay.example.services.CountryService;
import kz.abylay.example.services.MarketplaceService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarsController {
    private final CarsService carsService;
    private final CountryService countryService;
    private final MarketplaceService marketplaceService;

    public CarsController(CarsService carsService, CountryService countryService, MarketplaceService marketplaceService) {
        this.carsService = carsService;
        this.countryService = countryService;
        this.marketplaceService = marketplaceService;
    }

    @GetMapping("/table-cars")
    public String listPage(Model model){
        List<Cars> carsList = carsService.getAllCars();
        model.addAttribute("cars",carsList);
        return "table_cars";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable("id") Integer id, Model model){
        Cars c = carsService.getCarsById(id);
        model.addAttribute("cars", c);
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("marketplaces", marketplaceService.getAllMarketplace());
        return "editCars";
    }

    @PostMapping("/update-cars")
    public String updateCars(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("model") String model,
                             @RequestParam("tank") Double tank,
                             @RequestParam("countryId") Integer countryId){
        Country country = countryService.getCountryById(countryId);
        if (country != null){
            Cars c = new Cars(id, name, model, tank, country);
            carsService.updateCars(c);
            return "redirect:/table-cars";
        }
        return "redirect:/update/" + id;
    }

    @GetMapping("/delete")
    public String deleteCarsById(@RequestParam("carsId") Integer id){
        carsService.deleteCars(id);
        return "redirect:/table-cars";
    }

    @GetMapping("/add-cars-page")
    public String getAddCarsPage(@RequestParam(value = "fromAdmin", defaultValue = "false") Boolean fromAdmin, Model model) {
        model.addAttribute("countries", countryService.getAllCountry());
        if (fromAdmin){
            model.addAttribute("carsBackToPage", "/admin-panel");
        } else {
            model.addAttribute("carsBackToPage", "/table-cars");
        }
        return "addCars";
    }

    @PostMapping("/add-cars")
    public String addCars(@RequestParam("name") String name,
                          @RequestParam("model") String model,
                          @RequestParam("tank") Double tank,
                          @RequestParam("countryId") Integer countryId) {
        Country country = countryService.getCountryById(countryId);
        if (country != null){
            carsService.addCars(new Cars(null, name, model, tank, country));
            return "redirect:/table-cars";
        }
        return "redirect:/add-cars-page?error";
    }

}
