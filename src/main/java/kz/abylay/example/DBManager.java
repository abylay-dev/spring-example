package kz.abylay.example;

import kz.abylay.example.model.Cars;
import kz.abylay.example.model.Country;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class DBManager {
    @Getter
    private static final List<Cars> carsList = new ArrayList<>();
    @Getter
    private static final List<Country> countryList = new ArrayList<>();
    private static int id = 1;

    static {
        countryList.add(new Country(1,"Germany"));
        countryList.add(new Country(2,"Germany"));
        countryList.add(new Country(3, "Germany"));
        countryList.add(new Country(4,"USA"));
    /*    carsList.add(new Cars(id++, "BMW", "750L", 5.0,countryList.get(0)));
        carsList.add(new Cars(id++,"Mercedes","w223",6.0,countryList.get(1)));
        carsList.add(new Cars(id++,"Audi","RS6",5.5,countryList.get(2)));
        carsList.add(new Cars(id++,"Chevrolet Corvette","ZR1",6.2,countryList.get(3)));
    */}

    public static void addCars(Cars c){
        c.setId(id++);
        carsList.add(c);
    }

    public static void updateCars(Cars c){
        for (Cars cars: carsList){
            if (cars.getId() == c.getId()){
                carsList.set(carsList.indexOf(cars), c);
            }
        }
    }

    public static void deleteCarsById(Integer id){
        Iterator<Cars> carsIterator = carsList.iterator();
        while (carsIterator.hasNext()){
            Cars c = carsIterator.next();
            if (c.getId() == id){
                carsIterator.remove();
            }
        }
    }

    public static Country getCountryById(Integer countryId){
        List<Country> countries = DBManager.getCountryList();
        for (Country c: countries){
            if (c != null && c.getId() == countryId){
                return c;
            }
        }
        return null;
        }
    }
