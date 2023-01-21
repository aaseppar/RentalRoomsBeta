package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.Client;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Optional<Client> findByEmail(String email);
}