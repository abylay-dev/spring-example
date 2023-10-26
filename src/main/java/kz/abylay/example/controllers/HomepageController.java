package kz.abylay.example.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kz.abylay.example.model.*;
import kz.abylay.example.services.CarsService;
import kz.abylay.example.services.CountryService;
import kz.abylay.example.services.MarketplaceService;
import kz.abylay.example.services.UserService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomepageController {
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
    public String audi(){
        return "audiinfo";
    }

    @GetMapping("/porsche-information")
    public String porsche(){
        return "porscheinfo";
    }

}
