package kz.abylay.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data //@Getter + @Setter + @ToString
@NoArgsConstructor //пустой конструктор
@AllArgsConstructor //полный конструктор
@Entity
@Table(name="person_entity")
public class Person { //person_entity
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="person_id")
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @ManyToMany
    private List<Course> courses;

    public Person(Integer id, String firstname, String lastname, Integer age, Company company) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.company = company;
    }
}
