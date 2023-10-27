package kz.abylay.example.controllers;

import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Users;
import kz.abylay.example.services.RoleService;
import kz.abylay.example.services.UserService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/update-users")
    public String update(@RequestParam("userId") Integer userId,
                          @RequestParam("userName") String userName,
                          @RequestParam("userSurname") String userSurname,
                          @RequestParam("userEmail") String userEmail,
                          @RequestParam("userEmailPass") String userEmailPass){
        Users user = userService.getUserById(userId);
        if (user != null) {
            Users u = new Users(userId, userName, userSurname, userEmail, userEmailPass);
            userService.updateUser(u);
            return "redirect:/adminspanel";
        }
        return "redirect:/update-u/" + userId;
    }
    @GetMapping("/update-u/{id}")
    public String getUpdatePage(@PathVariable("id") Integer id, Model model){
        Users u = userService.getUserById(id);
        model.addAttribute("users", u);
        if (userService.getUserById(++id) != null) {
            model.addAttribute("next_user_id", id);
        }
        return "editUsers";
    }

    @GetMapping("/update-user-page")
    public String updateUserPage(Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUsers";
    }

    /*@GetMapping("/add-user-page")
    public String addUserPage(@RequestParam("firstname") String firstname,
                              @RequestParam("lastname") String lastname,
                              @RequestParam("age") Integer age,
                              @RequestParam("email") String email,
                              @RequestParam("balance") Double balance,
                              @RequestParam("role") Integer roleId){
        userService.addUser(new Users(firstname,lastname,age,email,balance,roleId));
        return "addusers";
    }*/

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
