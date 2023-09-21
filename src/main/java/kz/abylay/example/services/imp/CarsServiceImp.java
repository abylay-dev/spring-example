package kz.abylay.example.services.imp;

import kz.abylay.example.DBManager;
import kz.abylay.example.model.Cars;
import kz.abylay.example.repository.CarsRepository;
import kz.abylay.example.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarsServiceImp implements CarsService {
    @Autowired
    private CarsRepository carsRepository;

    @Override
    public List<Cars> getAllCars() {
        return carsRepository.findAll();
    }

    @Override
    public Cars getCarsById(Integer id) {
        return carsRepository.findById(id.longValue()).orElse(null);
    }

    @Override
    public void addCars(Cars c) {
        carsRepository.save(c);
    }

    @Override
    public void updateCars(Cars c) {
        carsRepository.save(c);
    }

    @Override
    public void deleteCars(Cars c) {

    }

    @Override
    public void deleteCars(Integer id) {
        carsRepository.deleteById(Long.valueOf(id));
    }

}
