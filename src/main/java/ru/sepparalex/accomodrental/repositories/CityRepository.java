package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.City;
@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    City findByName(String name) ;

}
