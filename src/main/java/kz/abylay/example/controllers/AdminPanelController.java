package kz.abylay.example.controllers;

import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Marketplace;
import kz.abylay.example.model.Users;
import kz.abylay.example.services.CarsService;
import kz.abylay.example.services.MarketplaceService;
import kz.abylay.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin-panel")
public class AdminPanelController {
    private final CarsService carsService;
    private final MarketplaceService marketplaceService;
    private final UserService userService;

    public AdminPanelController(CarsService carsService, MarketplaceService marketplaceService, UserService userService) {
        this.carsService = carsService;
        this.marketplaceService = marketplaceService;
        this.userService = userService;
    }

    @GetMapping
    public String showAllTables(Model model){
        List<Users> usersList = userService.getAllUsers();
        List<Marketplace> marketplaceList = marketplaceService.getAllMarketplace();
        List<Cars> carsList = carsService.getAllCars();

        model.addAttribute("users", usersList);
        model.addAttribute("marketplaces", marketplaceList);
        model.addAttribute("cars", carsList);

        return "adminspanel";
    }

    @GetMapping("/t-users")
    public String showAllUsers(Model model){
        List<Users> usersList = userService.getAllUsers();
        model.addAttribute("users", usersList);
        return "adminspanel";
    }

    @GetMapping("/t-marketplace")
    public String showAllMarketPlaces(Model model){
        List<Marketplace> marketplaceList = marketplaceService.getAllMarketplace();
        model.addAttribute("marketplaces", marketplaceList);
        return "adminspanel";
    }
    @GetMapping("/t-cars")
    public String showAllCars(Model model){
        List<Cars> carsList = carsService.getAllCars();
        model.addAttribute("cars", carsList);
        return "adminspanel";
    }



}
