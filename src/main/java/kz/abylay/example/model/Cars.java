package kz.abylay.example.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cars_id")
    private Integer id;
    @NotNull private String name;
    private String model;
    private double tank;
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;
    @ManyToMany
    private List<Marketplace> marketplaces;

    public Cars(Integer id,String name, String model, double tank, Country country) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.tank = tank;
        this.country = country;
    }

    public Cars(String name, String model, double tank) {
        this.name = name;
        this.model = model;
        this.tank = tank;
    }
}
