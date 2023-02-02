package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.Booking;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
List<Booking> findByEndtermBefore(Date value);
List<Booking>  findByBegintermAfter(Date value);
    @Query("select b from Booking b where b.client.id = ?1")
    Booking findByClientId(int id);
}
