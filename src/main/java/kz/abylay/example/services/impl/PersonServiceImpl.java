package kz.abylay.example.services.impl;

import kz.abylay.example.models.Course;
import kz.abylay.example.models.Person;
import kz.abylay.example.repository.PersonRepository;
import kz.abylay.example.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    @Override
    public void addCourse(Person p, Course course) {
        p.getCourses().add(course);
        this.updatePerson(p);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), (Collection<? extends GrantedAuthority>) user.getRole());
    }
}
