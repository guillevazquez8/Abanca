package com.proyecto.abanca.service.account;

import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.exceptions.NoFundsException;
import com.proyecto.abanca.exceptions.UnauthorizedException;
import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Savings;
import com.proyecto.abanca.repositories.account.BasicAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasicAccountService {

    private final BasicAccountRepository basicAccountRepository;

    private final SavingsService savingsService;

    private final CheckingService checkingService;

    public BasicAccount findById(Long id) {
        return accountExists(id);
    }

    public BasicAccount accessMyAccount(Long accountId, String username, String password) {
        BasicAccount myAccount = accountExists(accountId);
        usernameCorrespondsId(accountId, username);
        if (myAccount.getPrimaryOwner().getPassword() != password) {
            throw new UnauthorizedException("The password introduced is not correct.");
        }
        return basicAccountRepository.findByIdAndPrimaryOwner_UsernameAndPrimaryOwner_Password(accountId, username, password);
    }

    public Money accessMyAccountBalance(Long accountId, String username, String password) {
        return accessMyAccount(accountId, username, password).getBalance();
    }

    public Money accessAnyAccountBalance(Long accountId) {
        return accountExists(accountId).getBalance();
    }

    public Money modifyAnyAccountBalance(Long accountId, Long newBalance) {
        BasicAccount account = accountExists(accountId);
        account.setBalance(new Money(BigDecimal.valueOf(newBalance)));
        return account.getBalance();
    }

    public void deductAmountTransfer(Long amount, Long accountId) throws NoFundsException {
        BasicAccount accountOrigen = accountExists(accountId);
        if ((accountOrigen.getBalance().getAmount().longValue() - amount) < 0) {
            throw new NoFundsException("The account doesn't have enough funds to process this transfer");
        }
        Money newBalance = new Money(BigDecimal.valueOf(accountOrigen.getBalance().getAmount().longValue() - amount));
        accountOrigen.setBalance(newBalance);
        penaltyFee(amount, accountId);
    }

    public void depositAmountTransfer(Long amount, Long accountId) {
        BasicAccount accountOrigen = accountExists(accountId);
        Money newBalance = new Money(BigDecimal.valueOf(accountOrigen.getBalance().getAmount().longValue() + amount));
        accountOrigen.setBalance(newBalance);
    }

    public void penaltyFee(Long amount, Long accountId) {
        if (checkingService.findByIdOptional(accountId).isPresent()) {
            Checking checking = checkingService.findByIdOptional(accountId).get();

            if (checking.getBalance().getAmount().longValue() < checking.getMinimumBalance().getAmount().longValue()) {
                checking.setBalance(new Money(BigDecimal.valueOf(checking.getBalance().getAmount().longValue() - checking.getPenaltyFee().getAmount().longValue())));
            }
        }

        if (savingsService.findByIdOptional(accountId).isPresent()) {
            Savings savings = savingsService.findByIdOptional(accountId).get();

            if (savings.getBalance().getAmount().longValue() < savings.getMinimumBalance().getAmount().longValue()) {
                savings.setBalance(new Money(BigDecimal.valueOf(savings.getBalance().getAmount().longValue() - savings.getPenaltyFee().getAmount().longValue())));
            }
        }
    }

    public BasicAccount accountExists(Long id) {
        Optional<BasicAccount> accountOptional = basicAccountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new BadRequestException("The account introduced doesn't exist");
        }
        return accountOptional.get();
    }

    public void usernameCorrespondsId(Long accountId, String username) {
        BasicAccount myAccount = accountExists(accountId);
        if (!myAccount.getPrimaryOwner().getUsername().equalsIgnoreCase(username)) {
            throw new UnauthorizedException("Account ID " + accountId + " and username " + username + " don't fit.");
        }
    }

    public void nameCorrespondsId(Long accountId, String name) {
        BasicAccount myAccount = accountExists(accountId);
        if (!myAccount.getPrimaryOwner().getName().equalsIgnoreCase(name)) {
            if (!myAccount.getSecondaryOwner().getName().equalsIgnoreCase(name)) {
                throw new UnauthorizedException("Account ID " + accountId + " and name " + name + " don't fit.");
            }
        }
    }

}
