package kz.abylay.example.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kz.abylay.example.DBManager;
import kz.abylay.example.models.Company;
import kz.abylay.example.models.Course;
import kz.abylay.example.models.Person;
import kz.abylay.example.services.CompanyService;
import kz.abylay.example.services.CourseService;
import kz.abylay.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class ExampleController {
    private PersonService personService;
    private CompanyService companyService;
    private CourseService courseService;

    public ExampleController(PersonService pService, CompanyService companyService, CourseService courseService) {
        this.personService = pService;
        this.companyService = companyService;
        this.courseService = courseService;
    }


    @GetMapping("/first-page")
    public String welcomePage(Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("some_data", "123");
        cookie.setMaxAge(20);
        cookie.setValue("abylay");
        cookie.setAttribute("asdf", "zxcv");
        response.addCookie(cookie);
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
    public String getAddPersonPage(Model model) {
        List<Company> companies = companyService.getAllCompany();
        model.addAttribute("companies", companies);
        return "addPerson";
    }

    @PostMapping("/add-person")
    public String addPerson(@RequestParam("fname") String firstname,
                            @RequestParam("lname") String lastname,
                            @RequestParam("age") Integer age,
                            @RequestParam("companyId") Integer companyId) {
        System.out.println("companyId=" + companyId);
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            personService.addPerson(new Person(null, firstname, lastname, age, company));
            return "redirect:/first-page";
        }
        return "redirect:/add-person-page?error";
    }

    //todo update in frontend
    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable("id") Integer id, Model model) {
        List<Course> courses = courseService.getAllCourses();
        Person p = personService.getPersonById(id);
        if (p == null) {
            return "error";
        }
        model.addAttribute("person", p);
        model.addAttribute("courses", courses);
        return "editPerson";
    }

    @PostMapping("update-person")
    public String updatePerson(@RequestParam("id") Integer id,
                               @RequestParam("fname") String firstname,
                               @RequestParam("lname") String lastname,
                               @RequestParam("age") Integer age,
                               @RequestParam("companyId") Integer companyId) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            Person p = new Person(id, firstname, lastname, age, company);
            personService.updatePerson(p);
            return "redirect:/first-page";
        }
        return "redirect:/update/" + id;
    }

    @GetMapping("/delete")
    public String deletePersonById(@RequestParam("idshka") Integer id) {
        personService.deletePersonById(id);
        return "redirect:/first-page";
    }

    @PostMapping("/login")
    public String auth(@RequestParam("login") String login, @RequestParam("password") String password){
        if (login.equals("admin") && password.equals("admin123")){
            return "redirect:/first-page";
        }
        return "login-page";
    }


    @GetMapping("/add-course")
    public String addCourse(@RequestParam("person_id") Integer personId,
                            @RequestParam("course_id") Integer courseId){
        Course course = courseService.getById(courseId);
        Person person = personService.getPersonById(personId);
        personService.addCourse(person, course);
        return "redirect:/update/" + personId;
    }
    //changed from github

}


//@Bean, @Controller, @Component, @Service, @Repository, @Autowired, @RequestMapping(zaprosy)
//Spring container, IoC(Inversion of Control)

//@Bean
//Person p = new Person();