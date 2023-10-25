package kz.abylay.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name.split("_")[1]; //role_user = {role, user} => [1] = user
    }
}
