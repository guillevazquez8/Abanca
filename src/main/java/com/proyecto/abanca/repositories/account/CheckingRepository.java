package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
