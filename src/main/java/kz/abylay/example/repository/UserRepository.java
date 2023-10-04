package kz.abylay.example.repository;

import kz.abylay.example.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <Users, Integer> {
    List<Users> findByAgeBetween(int age1, int age2);
    Users findByEmail(String email);
}
