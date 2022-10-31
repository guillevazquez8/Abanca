package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.BasicAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasicAccountRepository extends JpaRepository<BasicAccount, Long> {

    @Query("""
            select b from BasicAccount b
            where b.id = ?1 and b.primaryOwner.username = ?2 and b.primaryOwner.password = ?3""")
    BasicAccount findByIdAndPrimaryOwner_UsernameAndPrimaryOwner_Password(Long id, String username, String password);
}
