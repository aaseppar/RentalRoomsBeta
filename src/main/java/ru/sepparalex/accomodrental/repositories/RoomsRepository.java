package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms,Integer> {

}
