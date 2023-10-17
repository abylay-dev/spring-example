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
    private int id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String email;
    private String password;
    private double balance;
    private String email_pass;

    @ManyToOne(fetch = FetchType.EAGER)
    private  Country country;

    @ManyToMany
    private List<Marketplace> marketplaces;

    @ManyToOne
    private Role role;

    public Users(String firstname, String lastname, Integer age, String email, String password, Double balance, String emailPass) {
    }
}
/* first name , last name , id , email, balance, email-pass (controller) update delete add findAll */

