package kz.abylay.example.services;

import kz.abylay.example.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Integer id);
}
