package kz.abylay.example.services.impl;

import kz.abylay.example.DBManager;
import kz.abylay.example.models.Person;
import kz.abylay.example.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public List<Person> getAllPerson() {
        return DBManager.getPersonList();
    }

    @Override
    public Person getPersonById(Integer id) {
        List<Person> personList = getAllPerson();
        for (Person p : personList){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public void addPerson(Person p) {
        DBManager.addPerson(p);
    }

    @Override
    public void updatePerson(Person p) {

    }

    @Override
    public void deletePersonById(Integer id) {

    }
}
