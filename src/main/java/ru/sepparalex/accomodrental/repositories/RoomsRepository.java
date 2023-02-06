package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.Rooms;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;
@Repository
public interface RoomsRepository extends JpaRepository<Rooms,Integer> {
   @Query("select r from Rooms r where r.city.name = ?1")
   List<Rooms> findByNameIgnoreCaseOrderByNameAsc(String name);
   @Query("select r from Rooms r where r.city.country.name=?1")
   List<Rooms> findByCountryNameIgnoreCase(String name);

//   @PersistenceUnit
//   EntityManagerFactory emf = null;
//
//   default List<Rooms> findByNameIgnoreCase(String name) {
//      EntityManager em = emf.createEntityManager();
//      List<Rooms> rooms = (List<Rooms>) em.createQuery("SELECT r FROM Rooms r join City c on r.city.id=c.id " +
//                      "join Country co on c.country.id = co.id where co.name=?1").setParameter(1,name).getResultList();
//      return rooms;
//   }

   List<Rooms> findByRating(Integer rating);
   @Query("select r from Rooms r where r.booking.price = ?1")
   List<Rooms> findByBookingPrice(Integer price);
   @Query("select r from Rooms r where r.booking.beginterm <= ?1")
   List<Rooms> findBeforeDate(Date date);
   @Query("select r from Rooms r where r.booking.endterm >= ?1")
   List<Rooms> findAfterDate(Date date);
   @Query("select r from Rooms r where r.client.id= ?1 and r.flagfree=1")
   Rooms findByClientId(int id);
   @Query("select r from Rooms r where r.id= ?1")
   Rooms findByRoomsId(int roomsId);
   @Query("select r from Rooms r where r.flagfree=1")
   List<Rooms> findByFlagFree();
}
