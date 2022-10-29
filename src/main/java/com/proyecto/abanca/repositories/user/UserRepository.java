package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
