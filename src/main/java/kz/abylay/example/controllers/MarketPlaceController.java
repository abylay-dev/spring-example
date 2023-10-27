package kz.abylay.example.controllers;

import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Country;
import kz.abylay.example.model.Marketplace;
import kz.abylay.example.services.CarsService;
import kz.abylay.example.services.CountryService;
import kz.abylay.example.services.MarketplaceService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MarketPlaceController {
    private final CarsService carsService;
    private final MarketplaceService marketplaceService;
    private final CountryService countryService;

    public MarketPlaceController(CarsService carsService, MarketplaceService marketplaceService, CountryService countryService) {
        this.carsService = carsService;
        this.marketplaceService = marketplaceService;
        this.countryService = countryService;
    }

    @GetMapping("/add-marketplace")
    public String addMarketplace(@RequestParam("cars_id")Integer carsId,
                                 @RequestParam("marketplace_id") Integer marketplaceId){
        Marketplace marketplace = marketplaceService.getMarketplaceById(marketplaceId);
        Cars cars = carsService.getCarsById(carsId);
        cars.getMarketplaces().add(marketplace);
        carsService.updateCars(cars);
        return "redirect:/update/" + carsId;
    }

    @GetMapping("/remove-marketplace")
    public String removeMarketplace(@RequestParam("cars_id")Integer carsId,
                                    @RequestParam("marketplace_id") Integer marketplaceId){
        Marketplace marketplace = marketplaceService.getMarketplaceById(marketplaceId);
        Cars cars = carsService.getCarsById(carsId);
        cars.getMarketplaces().remove(marketplace);
        carsService.updateCars(cars);
        return "redirect:/update/" + carsId;
    }


    @PostMapping("/add-marketplaces")
    public String addMarketplaces(@RequestParam("name") String name){
        marketplaceService.addMarketplace(new Marketplace(null, name));
        return "redirect:/admin-panel";
    }
    @GetMapping("/add-marketplace-page")
    public String addMarketPlacePage(Model model){
        model.addAttribute("location", countryService.getAllCountry());
        model.addAttribute("market", marketplaceService.getAllMarketplace());
        return "addmarketplace";
    }

    @GetMapping("/update-marketplace-pagebyid/{id}")
    public String getUpdateMPage(@PathVariable("id") Integer id, Model model){
        Marketplace m = marketplaceService.getMarketplaceById(id);
        Country c = countryService.getCountryById(id);
        model.addAttribute("marketplace", m);
        model.addAttribute("country", c);
        if (marketplaceService.getMarketplaceById(++id) != null) {
            model.addAttribute("next_marketplace_id", id);
        }
        return "editMarketplace";
    }

    @PostMapping("/update-marketplace")
    public String updateMarketplace(@RequestParam("id") Integer id,
                             @RequestParam("mname") String mname){
        Marketplace marketplace = marketplaceService.getMarketplaceById(id);
        if (marketplace != null){
            Marketplace m = new Marketplace(id, mname);
            marketplaceService.updateMarketplace(m);
            return "redirect:/admin-panel";
        }
        return "redirect:/update-marketplace-pagebyid/" + id;
    }

    @GetMapping("/delete-marketplace")
    public String deleteMarketplaceById(@RequestParam("marketplaceId") Integer id){
        marketplaceService.deleteMarketplace(id);
        return "redirect:/admin-panel";
    }

}
