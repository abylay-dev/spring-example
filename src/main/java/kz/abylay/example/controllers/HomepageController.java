package kz.abylay.example.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kz.abylay.example.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    private final CarsService carsService;

    public HomepageController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/")
    public String home(HttpServletResponse response) {
        Cookie cookie = new Cookie("date", "1");
        response.addCookie(cookie);
        cookie.setMaxAge(600);
        cookie.setValue("2");
        cookie.setAttribute("etad", "3");
        return "index";
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
    public String audi(Model model){
        model.addAttribute("i", 1);
        model.addAttribute("carsAudi", carsService.findCars("Audi"));
        return "audiinfo";
    }

    @GetMapping("/porsche-information")
    public String porsche(){
        return "porscheinfo";
    }

}
