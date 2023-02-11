package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
public Country findByName(String name);
}
