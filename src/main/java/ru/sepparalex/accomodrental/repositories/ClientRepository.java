package ru.sepparalex.accomodrental.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sepparalex.accomodrental.models.Client;
import ru.sepparalex.accomodrental.models.Rooms;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Optional<Client> findByEmail(String email);
    @Query("select c from Client c where c.login=?1 and c.email=?2")
    Client findByLoginAndEmail(String login, String email);

}