package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsRepository extends JpaRepository<Admins, Long> {
}
