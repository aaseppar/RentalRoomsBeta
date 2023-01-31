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
@Table(name="city")
public class City {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "countryid",referencedColumnName = "id")
    private Country country;
    @OneToMany(mappedBy="city")
    @JsonIgnore
    private List<Rooms> roomsList;
    @OneToMany(mappedBy="city")
    @JsonIgnore
    private List<Client> clientList;

    public City(int id,String name,Country country) {
        this.id = id;
        this.name = name;
        this.country=country;
    }
}
