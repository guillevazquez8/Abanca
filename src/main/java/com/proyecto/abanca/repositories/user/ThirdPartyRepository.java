package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {

    @Query("select t from ThirdParty t where t.hashedKey = ?1")
    Optional<ThirdParty> findByHashedKey(String hashedKey);

}
