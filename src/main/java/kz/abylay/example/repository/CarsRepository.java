package kz.abylay.example.repository;

import kz.abylay.example.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
    List<Cars> findCarsByNameOrderById(String name);
}
