package kz.abylay.example.controllers;

import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Role;
import kz.abylay.example.model.Users;
import kz.abylay.example.services.RoleService;
import kz.abylay.example.services.UserService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.nio.DoubleBuffer;

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
    public String update(@RequestParam("id") Integer id,
                          @RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("age") Integer age,
                          @RequestParam("email") String email,
                          @RequestParam("balance") Double balance,
                          @RequestParam("roleId") Integer roleId
    ){
        Role role = roleService.getRoleById(roleId);
        if (role != null) {
            Users user = userService.getUserById(id);
            if (user != null) {
                Users u = new Users(id, name, surname, age, email, balance, role);
                userService.updateUser(u);
                return "redirect:/admin-panel";
            }
        }
        return "redirect:/update-u/" + id;
    }
    @GetMapping("/update-u/{id}")
    public String getUpdatePage(@PathVariable("id") Integer id, Model model){
        Users u = userService.getUserById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", u);
        if (userService.getUserById(++id) != null) {
            model.addAttribute("next_user_id", id);
        }
        return "editUsers";
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
        if (roleId == -1){
            return "redirect:/authorize/login";
        }
        return "redirect:/admin-panel";
    }

    @GetMapping("/remove-users")
    public String removeUser(@RequestParam("user_id")Integer userId){
        userService.deleteUser(userId);
        return "redirect:/admin-panel";
    }

}
