package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
