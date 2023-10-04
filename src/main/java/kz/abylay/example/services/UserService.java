package kz.abylay.example.services;


import kz.abylay.example.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<Users> getAllUsers();
    Users getUserById(Integer id);

    void addUser(Users u);

    void updateUser(Users u);

    void deleteUser(Users u);
}
