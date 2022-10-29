package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
