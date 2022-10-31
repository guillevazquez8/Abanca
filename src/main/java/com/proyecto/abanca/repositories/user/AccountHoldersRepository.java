package com.proyecto.abanca.repositories.user;

import com.proyecto.abanca.model.user.AccountHolders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountHoldersRepository extends JpaRepository<AccountHolders, Long> {
    @Query("""
            select a from AccountHolders a inner join a.transfersOrdered transfersOrdered
            where transfersOrdered.accountOrigen.id = ?1""")
    AccountHolders findByTransfersOrdered_AccountOrigen_Id(Long id);


}
