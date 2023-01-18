package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sepparalex.accomodrental.models.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {
    List<City> findAll();
}
