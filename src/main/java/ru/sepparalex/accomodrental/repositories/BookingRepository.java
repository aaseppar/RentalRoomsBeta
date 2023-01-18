package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sepparalex.accomodrental.models.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

}
