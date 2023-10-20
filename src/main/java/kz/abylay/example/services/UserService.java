package kz.abylay.example.services;


import kz.abylay.example.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<Users> getAllUsers();
    Users getUserById(Integer id);

    void addUser(Users u) throws RoleNotFoundException;

    void updateUser(Users u);

    void deleteUser(Integer u);
}
