package kz.abylay.example.controllers;

import jakarta.annotation.security.PermitAll;
import kz.abylay.example.model.Users;
import kz.abylay.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.RoleNotFoundException;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update-user")
    public String update(@RequestParam("userId") Integer userId,
                          @RequestParam("userName") String userName,
                          @RequestParam("userSurname") String userSurname,
                          @RequestParam("userEmail") String userEmail,
                          @RequestParam("userEmailPass") String userEmailPass){
        Users user = userService.getUserById(userId);
        if (user != null) {
            userService.updateUser(user);
            return "redirect:/update/" + userId;
        }
        return "redirect:/error";
    }

    @PostMapping("/add-user")
    public String addUsers(@RequestParam("firstname") String firstname,
                           @RequestParam("lastname") String lastname,
                           @RequestParam("age") Integer age,
                           @RequestParam("balance") Double balance,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("rePassword") String  rePassword) throws RoleNotFoundException {
        userService.addUser(new Users(null, firstname, lastname, age, email, password, balance, rePassword));
        return "redirect:/";
    }

    @GetMapping("/remove-users")
    public String removeUser(@RequestParam("user_id")Integer userId){
        userService.deleteUser(userId);
        return "redirect:/index";
    }


}
