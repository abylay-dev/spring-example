package kz.abylay.example.controllers;

import kz.abylay.example.model.Users;
import kz.abylay.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/update-user")
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


}
