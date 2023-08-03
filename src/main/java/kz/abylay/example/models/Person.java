package kz.abylay.example.models;

import lombok.*;

@Data //@Getter + @Setter + @ToString
@NoArgsConstructor //пустой конструктор
@AllArgsConstructor //полный конструктор
public class Person {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;
}
