package kz.abylay.example.services.imp;

import kz.abylay.example.model.Role;
import kz.abylay.example.repository.RoleRepository;
import kz.abylay.example.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    public RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void createRoles(){
        roleRepository.saveAllAndFlush(List.of(new Role(1, "ROLE_USER"),
                                                new Role(2, "ROLE_ADMIN"),
                                                new Role(3, "ROLE_MODERATOR"))
        );
    }
}
