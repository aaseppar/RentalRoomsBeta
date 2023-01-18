package ru.sepparalex.accomodrental.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="country")
public class Country {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy="country")
    @JsonIgnore
    private List<City> cityList;

}
