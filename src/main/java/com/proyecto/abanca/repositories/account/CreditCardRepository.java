package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
