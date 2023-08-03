package kz.abylay.example;

import kz.abylay.example.models.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    @Getter
    private static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person(1, "Dimash", "asdf", 654));
        personList.add(new Person(2, "Perizat", "asdf", 654));
        personList.add(new Person(3, "Abylay", "asdf", 654));
    }

    public static void addPerson(Person p){
        personList.add(p);
    }
}
