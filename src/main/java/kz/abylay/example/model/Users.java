package kz.abylay.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String email;
    private String password;
    private Double balance;

    @Transient
    private String rePassword;

    @ManyToOne
    private Role role;

    public Users(Integer id, String firstname, String lastname, Integer age, String email, String password, Double balance, String rePassword) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.rePassword = rePassword;
    }

    public Users(Integer id, String firstname, String lastname, Integer age, String email, Double balance, Role role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.balance = balance;
        this.role = role;
    }
}

