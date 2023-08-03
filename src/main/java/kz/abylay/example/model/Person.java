package kz.abylay.example.model;

import lombok.*;

@Data //@Getter + @Setter + @ToString
@NoArgsConstructor //пустой конструктор
@AllArgsConstructor //полный конструктор
public class Person {
    private String firstname;
    private String lastname;
    private Integer age;
}
