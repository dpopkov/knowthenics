package ru.dpopkov.knowthenics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dpopkov.knowthenics.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
