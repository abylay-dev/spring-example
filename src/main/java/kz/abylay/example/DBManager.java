package kz.abylay.example;

import kz.abylay.example.models.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBManager {
    @Getter
    private static List<Person> personList = new ArrayList<>();

    private static Integer id = 1;

    static {
        personList.add(new Person(id++, "Dimash", "asdf", 654));
        personList.add(new Person(id++, "Perizat", "asdf", 654));
        personList.add(new Person(id++, "Abylay", "asdf", 654));
    }

    public static void addPerson(Person p) {
        p.setId(id++);
        personList.add(p);
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
}
