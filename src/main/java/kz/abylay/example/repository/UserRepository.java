package kz.abylay.example.repository;

import kz.abylay.example.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <Users, Integer> {
}
