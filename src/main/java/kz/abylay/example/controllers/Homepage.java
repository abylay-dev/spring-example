package kz.abylay.example.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Country;
import kz.abylay.example.model.Marketplace;
import kz.abylay.example.model.Person;
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

@Controller("/")
public class Homepage {
    private final CarsService carsService;
    private final CountryService countryService;
    private final MarketplaceService marketplaceService;

    public Homepage(CarsService carsService, CountryService countryService, MarketplaceService marketplaceService){
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

    @GetMapping("/carlist")
    public String getCarsById(@RequestParam("id") Integer id, Model model){
        Cars c = carsService.getCarsById(id);
        model.addAttribute("c",c);
        return "carsId";
    }

    @GetMapping("/")
        public String home(HttpServletResponse response){
        Cookie cookie = new Cookie("date", "1");
        response.addCookie(cookie);
        cookie.setMaxAge(600);
        cookie.setValue("2");
        cookie.setAttribute("etad", "3");
        return "index";
        }



    /*@PostMapping("/second")
    public String second_Page(@RequestParam("login") String login,
                              @RequestParam("password") String password,
                              Model model){
        Person p = new Person(login, password);
        System.out.println("login : "  + login + " password : " + password);
        model.addAttribute("person", p);
        List<Cars> carsList = carsService.getAllCars();
        model.addAttribute("cars",carsList);
        return "table_cars";}
*/
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

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/bmw-information")
    public String bmw(){
        return "bmwinfo";
    }
    @GetMapping("/mercedes-information")
    public String mercedes(){
        return "mercedesinfo";
    }
    @GetMapping("/audi-information")
    public String audi(){
        return "audiinfo";
    }
    @GetMapping("/porsche-information")
    public String porsche(){
        return "porscheinfo";
    }
    @GetMapping("/add-cars-page")
    public String getAddCarsPage(Model model) {
        model.addAttribute("countries", countryService.getAllCountry());
        return "addCars";
    }

    @PostMapping("/add-cars")
    public String addPerson(@RequestParam("name") String name,
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
}
