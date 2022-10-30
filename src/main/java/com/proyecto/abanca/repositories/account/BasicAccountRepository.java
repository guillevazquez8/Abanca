package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.BasicAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BasicAccountRepository extends JpaRepository<BasicAccount, Long> {

    @Query("select b from BasicAccount b where b.primaryOwner.username = ?1 and b.primaryOwner.password = ?2")
    List<BasicAccount> findByPrimaryOwner_UsernameAndPrimaryOwner_Password(String username, String password);



}
