package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.AccountHolders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHoldersRepository extends JpaRepository<AccountHolders, Long> {
}
