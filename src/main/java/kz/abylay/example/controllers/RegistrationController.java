package kz.abylay.example.controllers;

import kz.abylay.example.model.Users;
import kz.abylay.example.repository.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.Role;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
/*
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Users users, Model model){
        Users userFromDB = userRepository.findByEmail(users.getEmail());
        if (userFromDB != null){
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        *//*users.setRole(Collections.singleton(new Role( "ROLE_USER", 1L)));
*//*
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

        userRepository.save(users);
        return "redirect:/login";
    }*/
}
