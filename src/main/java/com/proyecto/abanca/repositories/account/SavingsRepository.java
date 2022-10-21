package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
