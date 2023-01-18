package ru.sepparalex.accomodrental.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;


@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="beginterm")
    private Date beginterm;
    @Column(name="endterm")
    private Date endterm;
    @Column(name="price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "clientid",referencedColumnName = "id")
    private Client client=new Client();

    @OneToMany(mappedBy="booking")
    @JsonIgnore
    private List<Rooms> roomsList;

    public Booking(int id, Date beginterm, Date endterm, int price, Client client, List<Rooms> roomsList) {
        this.id = id;
        this.beginterm = beginterm;
        this.endterm = endterm;
        this.price = price;
     }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", beginterm=" + beginterm +
                ", endterm=" + endterm +
                ", price=" + price +
                '}';
    }
}
