package kz.abylay.example.controllers;

import kz.abylay.example.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExampleController {
    private Person p;

    @GetMapping("/first-page")
    public String welcomePage() {
        return "home";
    }

    @PostMapping("/second")
    public String secondPage(@RequestParam("fname") String firstname,
                             @RequestParam("lname") String lastname,
                             @RequestParam("age") Integer age,
                             Model model) {
        p = new Person(firstname, lastname, age);
        System.out.println(firstname + " " + lastname + " " + age);
        if (age<0){
            return "error";
        }
        model.addAttribute("person", p);
        return "about";
    }
    
    //changed from github

}
