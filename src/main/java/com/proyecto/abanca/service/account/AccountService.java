package com.proyecto.abanca.service.account;

import com.proyecto.abanca.exceptions.UnauthorizedException;
import com.proyecto.abanca.model.account.Account;
import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.repositories.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final BasicAccountService basicAccountService;

    public void secretKeyCorrespondsId(Long id, String secretKey) {
        Account account = accountRepository.findById(basicAccountService.accountExists(id).getId()).get();
        if (!account.getSecretKey().equals(secretKey)) {
            throw new UnauthorizedException("Account ID " + id + " and provided secret key don't fit.");
        }
    }

}