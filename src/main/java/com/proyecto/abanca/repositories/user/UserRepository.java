package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}
