package kz.abylay.example.services;

import kz.abylay.example.models.Course;
import kz.abylay.example.models.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonService extends UserDetailsService {
    List<Person> getAllPerson();
    Person getPersonById(Integer id);
    void addPerson(Person p);
    void updatePerson(Person p);
    void deletePersonById(Integer id);

    void addCourse(Person p, Course course);
}
