package kz.abylay.example.controllers;

import kz.abylay.example.models.Person;
import kz.abylay.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ExampleController {
    @Autowired
    private PersonService personService;

    @GetMapping("/first-page")
    public String welcomePage(Model model) {
        List<Person> personList = personService.getAllPerson();
        model.addAttribute("people", personList);
        return "home";
    }

    @GetMapping("/person") //http://localhost:8080/person?id=1
    public String getPersonById(@RequestParam("id") Integer id, Model model) {
        Person p = personService.getPersonById(id);
        model.addAttribute("p", p);
        return "personById";
    }

    @PostMapping("/second")
    public String secondPage(@RequestParam("fname") String firstname,
                             @RequestParam("lname") String lastname,
                             @RequestParam("age") Integer age,
                             Model model) {
//        p = new Person(firstname, lastname, age);
        System.out.println(firstname + " " + lastname + " " + age);
        if (age < 0) {
            return "error";
        }
//        model.addAttribute("person", p);
        return "about";
    }

    //changed from github

}
