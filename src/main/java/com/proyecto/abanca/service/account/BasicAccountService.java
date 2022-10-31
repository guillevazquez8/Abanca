package com.proyecto.abanca.service.account;

import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.exceptions.UnauthorizedException;
import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.repositories.account.BasicAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasicAccountService {

    private final BasicAccountRepository basicAccountRepository;

    public BasicAccount accessMyAccount(Long accountId, String username, String password) {
        BasicAccount myAccount = optionalNotEmpty(accountId);
        if (myAccount.getPrimaryOwner().getUsername() != username) {
            throw new UnauthorizedException("The account you're trying to access, wiht id " + accountId + " is not yours");
        }
        return basicAccountRepository.findByIdAndPrimaryOwner_UsernameAndPrimaryOwner_Password(accountId, username, password);
    }

    public Money accessMyAccountBalance(Long accountId, String username, String password) {
        return accessMyAccount(accountId, username, password).getBalance();
    }

    public Money accessAnyAccountBalance(Long accountId) {
        return optionalNotEmpty(accountId).getBalance();
    }

    public Money modifyAnyAccountBalance(Long accountId, Long newBalance) {
        BasicAccount account = optionalNotEmpty(accountId);
        account.setBalance(new Money(BigDecimal.valueOf(newBalance)));
        return account.getBalance();
    }

    public BasicAccount optionalNotEmpty(Long id) {
        Optional<BasicAccount> accountOptional = basicAccountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new BadRequestException("The introduced account doesn't exist");
        }
        return accountOptional.get();
    }

}
