package kz.abylay.example;

import kz.abylay.example.models.Company;
import kz.abylay.example.models.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBManager {
    @Getter
    private static List<Person> personList = new ArrayList<>();

    @Getter
    private static List<Company> companies = new ArrayList<>();

    private static Integer id = 1;

    static {
        companies.add(new Company(1, "The Coca Cola"));
        companies.add(new Company(2, "RG Brands"));
        companies.add(new Company(3, "Meta"));
        companies.add(new Company(4, "Kaspi"));

//        personList.add(new Person(id++, "Dimash", "asdf", 654, companies.get(2)));
//        personList.add(new Person(id++, "Perizat", "asdf", 654, companies.get(3)));
//        personList.add(new Person(id++, "Abylay", "asdf", 654, companies.get(0)));
    }

    public static void addPerson(Person p) {
        p.setId(id++);
        personList.add(p);
    }

    public static void updatePerson(Person p) {
        for (Person person : personList) {
            if (person.getId() == p.getId()) {
                personList.set(personList.indexOf(person), p);
            }
        }
    }

    public static void deletePersonById(Integer id) {
        Iterator<Person> it = personList.iterator();
        while (it.hasNext()) {
            Person p = it.next();
            if (p.getId() == id) {
                it.remove();
            }
        }
    }

    public static Company getCompanyById(int companyId) {
        //todo доделать это
        return null;
    }
}
