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
@Table(name="rating")
public class Rating {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="value")
    private int value;
    @OneToMany(mappedBy="rating")
    @JsonIgnore
    private List<Client> clientList;

    public Rating(int id, int value) {
        this.id = id;
        this.value = value;
    }
}