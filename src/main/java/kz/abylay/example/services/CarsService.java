package kz.abylay.example.services;


import kz.abylay.example.model.Cars;

import java.util.List;

public interface CarsService {
    List<Cars> getAllCars();
    Cars getCarsById(Integer id);
    void addCars(Cars c);
    void updateCars(Cars c);
    void deleteCars(Integer id);

}
