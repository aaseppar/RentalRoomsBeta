package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.Booking;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
List<Booking> findByStartDateBefore(Date value);
List<Booking>  findByStartDateAfter(Date value);
}
