package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.ERole;
import com.proyecto.abanca.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
