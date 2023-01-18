package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sepparalex.accomodrental.models.Country;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    List<Country> findAll();
}
