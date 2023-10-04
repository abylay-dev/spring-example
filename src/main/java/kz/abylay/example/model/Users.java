package kz.abylay.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    @Id
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private double balance;
    private String email_pass;
}
/* first name , last name , id , email, balance, email-pass (controller) update delete add findAll */

