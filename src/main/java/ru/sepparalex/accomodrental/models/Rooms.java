package ru.sepparalex.accomodrental.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="rooms")
public class Rooms {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private int id;
   @Column(name="rating")
   private int rating;
   @Column(name="flagFree")
   private int flagfree;
   @ManyToOne
   @JoinColumn(name = "clientid",referencedColumnName = "id")
   private Client client;
   @ManyToOne
   @JoinColumn(name = "cityid",referencedColumnName = "id")
   private City city;
   @ManyToOne
   @JoinColumn(name = "bookingid",referencedColumnName = "id")
   private Booking booking;

   public Rooms(int rating, int flagfree) {
      this.rating = rating;
      this.flagfree = flagfree;
   }
}
