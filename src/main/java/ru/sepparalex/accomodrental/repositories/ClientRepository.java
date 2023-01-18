package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sepparalex.accomodrental.models.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findAll();
}