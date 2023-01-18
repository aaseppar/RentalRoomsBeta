package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sepparalex.accomodrental.models.Rooms;

import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms,Integer> {
    List<Rooms> findAll();
}
