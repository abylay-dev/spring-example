package kz.abylay.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExampleController {

    @GetMapping("/first-page")
    public String welcomePage() {
        return "home";
    }

    @PostMapping("/second")
    public String secondPage(@RequestParam("fname") String firstname, @RequestParam("lname") String lastname, @RequestParam("age") Integer age) {
        System.out.println(firstname + " " + lastname + " " + age);
        return "about";
    }

}
