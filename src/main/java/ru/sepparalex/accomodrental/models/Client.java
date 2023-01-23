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
@Table(name="client")
public class Client {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="userfullname")
    private String userfullname;
    @Column(name="login")
    private String login;
    @Column(name="passwd")
    private String password;
    @Column(name="birthday")
    private String birthday;
    @Column(name="email")
    private String email;

    @Column(name = "role")
    @Enumerated(value=EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value=EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Booking> listBooking;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Rooms> listRooms;

    @ManyToOne
    @JoinColumn(name = "cityid",referencedColumnName = "id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "ratingid",referencedColumnName = "id")
    private Rating rating;

    public Client(String userfullname, String login, String passwd, String birthday, String email,
    String role,String status) {
        this.userfullname = userfullname;
        this.login = login;
        this.password = passwd;
        this.birthday = birthday;
        this.email = email;
        this.role = Role.valueOf(role);
        this.status = Status.valueOf(status);
    }

}
