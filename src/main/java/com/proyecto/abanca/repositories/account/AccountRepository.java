package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
