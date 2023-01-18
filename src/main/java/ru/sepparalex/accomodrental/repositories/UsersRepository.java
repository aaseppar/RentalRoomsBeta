package ru.sepparalex.accomodrental.repositories;

import ru.sepparalex.accomodrental.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
