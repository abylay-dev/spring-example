package kz.abylay.example.models;

import jakarta.persistence.*;
import lombok.*;

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

//    private Company company;
}
