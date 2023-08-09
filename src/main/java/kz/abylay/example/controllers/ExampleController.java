package kz.abylay.example.controllers;

import kz.abylay.example.models.Person;
import kz.abylay.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExampleController {
    private PersonService personService;

    public ExampleController(PersonService pService) {
        this.personService = pService;
    }


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

    @GetMapping("/add-person-page")
    public String getAddPersonPage() {
        return "addPerson";
    }

    @PostMapping("/add-person")
    public String addPerson(@RequestParam("fname") String firstname,
                            @RequestParam("lname") String lastname,
                            @RequestParam("age") Integer age) {
        personService.addPerson(new Person(null, firstname, lastname, age));
        return "redirect:/first-page";
    }

    @GetMapping("/delete")
    public String deletePersonById(@RequestParam("idshka") Integer id) {
        personService.deletePersonById(id);
        return "redirect:/first-page";
    }


    //changed from github

}


//@Bean, @Controller, @Component, @Service, @Repository, @Autowired, @RequestMapping(zaprosy)
//Spring container, IoC(Inversion of Control)

//@Bean
//Person p = new Person();