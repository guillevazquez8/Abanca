package com.proyecto.abanca.security.repository;

import com.proyecto.abanca.security.models.ERole;
import com.proyecto.abanca.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
