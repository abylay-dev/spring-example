package kz.abylay.example.services.impl;

import kz.abylay.example.DBManager;
import kz.abylay.example.models.Person;
import kz.abylay.example.repository.PersonRepository;
import kz.abylay.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //@Component @Repository
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPerson() {
//        return personRepository.findAll();
        return personRepository.findByAgeBetween(100, 1000);
    }

    @Override
    public Person getPersonById(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    @Override
    public void addPerson(Person p) {
        personRepository.saveAndFlush(p);
    }

    @Override
    public void updatePerson(Person p) {
        personRepository.saveAndFlush(p);
    }

    @Override
    public void deletePersonById(Integer id) {
        personRepository.deleteById(id);
    }
}
