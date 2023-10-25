package kz.abylay.example.controllers;

import kz.abylay.example.model.Users;
import kz.abylay.example.services.RoleService;
import kz.abylay.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.RoleNotFoundException;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final RoleService roleService;

    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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

    @GetMapping("/add-user-page")
    public String addUserPage(Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        return "addusers";
    }

    @PostMapping("/add-user")
    public String addUsers(@RequestParam("firstname") String firstname,
                           @RequestParam("lastname") String lastname,
                           @RequestParam("age") Integer age,
                           @RequestParam("balance") Double balance,
                           @RequestParam("email") String email,
                           @RequestParam(value = "password", defaultValue = "123") String password,
                           @RequestParam(value = "rePassword", defaultValue = "123") String  rePassword,
                           @RequestParam(value = "roleId", defaultValue = "-1") Integer roleId) throws RoleNotFoundException {
        userService.addUser(new Users(null, firstname, lastname, age, email, password, balance, rePassword), roleId);
        return "redirect:/";
    }

    @GetMapping("/remove-users")
    public String removeUser(@RequestParam("user_id")Integer userId){
        userService.deleteUser(userId);
        return "redirect:/index";
    }


}
