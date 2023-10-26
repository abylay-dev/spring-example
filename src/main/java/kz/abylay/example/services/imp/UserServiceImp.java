package kz.abylay.example.services.imp;


import kz.abylay.example.model.Role;
import kz.abylay.example.model.Users;
import kz.abylay.example.repository.RoleRepository;
import kz.abylay.example.repository.UserRepository;
import kz.abylay.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public void addUser(Users u, Integer roleId) throws RoleNotFoundException {
        Role userRole = roleRepository.findRoleByName("ROLE_USER");

        if (roleId > 0){
            userRole = roleRepository.findById(roleId).orElse(userRole);
        }

        if (userRole == null){
            throw new RoleNotFoundException("USER role not found");
        }

        if (u.getPassword().equals(u.getRePassword())){
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            u.setRole(userRole);
            userRepository.save(u);
        }
    }

    @Override
    public void updateUser(Users u) {
        userRepository.save(u);
    }

    @Override
    public void deleteUser(Integer u) {
        userRepository.deleteById(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Set.of(user.getRole()));
    }

}
