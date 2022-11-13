package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.AccountHolderDto;
import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.exceptions.NoFundsException;
import com.proyecto.abanca.exceptions.UnauthorizedException;
import com.proyecto.abanca.model.account.*;
import com.proyecto.abanca.repositories.account.BasicAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasicAccountService {

    private final BasicAccountRepository basicAccountRepository;
    private final SavingsService savingsService;
    private final CreditCardService creditCardService;
    private final CheckingService checkingService;

    public BasicAccount findById(Long id) {
        return accountExists(id);
    }

    public BasicAccount accessMyAccount(Long accountId, AccountHolderDto accountHolderDto) {
        usernameAndPasswordCorrespondsAccount(accountId, accountHolderDto);
        return basicAccountRepository.findByIdAndPrimaryOwner_UsernameAndPrimaryOwner_Password(accountId, accountHolderDto.getUsername(), accountHolderDto.getPassword());
    }

    public Money accessMyAccountBalance(Long accountId, AccountHolderDto accountHolderDto) {
        BasicAccount account = accessMyAccount(accountId, accountHolderDto);
        applyInterestRate(account.getId());
        return account.getBalance();
    }

    public Money accessAnyAccountBalance(Long accountId) {
        applyInterestRate(accountId);
        return accountExists(accountId).getBalance();
    }

    public BasicAccount modifyAnyAccountBalance(Long accountId, Long newBalance) {
        BasicAccount account = accountExists(accountId);
        account.setBalance(new Money(BigDecimal.valueOf(newBalance)));
        return basicAccountRepository.save(account);
    }

    public void deductAmountTransfer(Long amount, Long accountId) throws NoFundsException {
        BasicAccount accountOrigen = accountExists(accountId);
        if (accountOrigen.getBalance().getAmount().subtract(BigDecimal.valueOf(amount)).compareTo(BigDecimal.ZERO) < 0) {
            throw new NoFundsException("The account doesn't have enough funds to process this transfer");
        }
        Money newBalance = new Money(accountOrigen.getBalance().getAmount().subtract(BigDecimal.valueOf(amount)));
        accountOrigen.setBalance(newBalance);
        penaltyFee(amount, accountId);
    }

    public void depositAmountTransfer(Long amount, Long accountId) {
        BasicAccount accountOrigen = accountExists(accountId);
        Money newBalance = new Money(accountOrigen.getBalance().getAmount().add(BigDecimal.valueOf(amount)));
        accountOrigen.setBalance(newBalance);
    }

    //method to apply penalty fee, 40 USD, if the balance gets lower than the minimum balance
    public void penaltyFee(Long amount, Long accountId) {
        if (checkingService.findByIdOptional(accountId).isPresent()) {
            Checking checking = checkingService.findByIdOptional(accountId).get();

            if (checking.getBalance().getAmount().longValue() < checking.getMinimumBalance().getAmount().longValue()) {
                checking.setBalance(new Money(checking.getBalance().getAmount().subtract(checking.getPenaltyFee().getAmount())));
            }
        }

        if (savingsService.findByIdOptional(accountId).isPresent()) {
            Savings savings = savingsService.findByIdOptional(accountId).get();

            if (savings.getBalance().getAmount().longValue() < savings.getMinimumBalance().getAmount().longValue()) {
                savings.setBalance(new Money(savings.getBalance().getAmount().subtract(savings.getPenaltyFee().getAmount())));
            }
        }
    }

    //method to apply interest rate one year after creating the account, and every year after the interest rate was applied
    public void applyInterestRate(Long accountId) {
        if (savingsService.findByIdOptional(accountId).isPresent()) {
            Savings savings = savingsService.findById(accountId);
            if (savings.getInterestRateDateApplied().equals(null)) { //if interest rate hasnt been applied yet
                if (LocalDate.now().isAfter(LocalDate.of(savings.getCreationDate().getYear() + 1, savings.getCreationDate().getMonth(), savings.getCreationDate().getDayOfMonth()))) {
                    savings.setBalance(new Money(savings.getBalance().getAmount().add(savings.getBalance().getAmount().multiply(savings.getInterestRate()))));
                    savings.setInterestRateDateApplied(LocalDate.now());
                }
            }else if (LocalDate.now().isAfter(LocalDate.of(savings.getInterestRateDateApplied().getYear() + 1, savings.getInterestRateDateApplied().getMonth(), savings.getInterestRateDateApplied().getDayOfMonth()))) {
                    savings.setBalance(new Money(savings.getBalance().getAmount().add(savings.getBalance().getAmount().multiply(savings.getInterestRate()))));
                    savings.setInterestRateDateApplied(LocalDate.now());
            }
        }else if (creditCardService.findByIdOptional(accountId).isPresent()) {
            CreditCard creditCard = creditCardService.findById(accountId);
            if (creditCard.getInterestRateDateApplied().equals(null)) {
                if (LocalDate.now().isAfter(LocalDate.of(creditCard.getCreationDate().getYear(), creditCard.getCreationDate().getMonth().plus(1), creditCard.getCreationDate().getDayOfMonth()))) {
                    creditCard.setBalance(new Money(creditCard.getBalance().getAmount().add(creditCard.getBalance().getAmount().multiply(creditCard.getInterestRate().divide(BigDecimal.valueOf(12))))));
                    creditCard.setInterestRateDateApplied(LocalDate.now());
                } else if (LocalDate.now().isAfter(LocalDate.of(creditCard.getInterestRateDateApplied().getYear(), creditCard.getInterestRateDateApplied().getMonth().plus(1), creditCard.getInterestRateDateApplied().getDayOfMonth()))) {
                    creditCard.setBalance(new Money(creditCard.getBalance().getAmount().add(creditCard.getBalance().getAmount().multiply(creditCard.getInterestRate().divide(BigDecimal.valueOf(12))))));
                    creditCard.setInterestRateDateApplied(LocalDate.now());
                }
            }
        }
    }

    public BasicAccount accountExists(Long accountId) {
        Optional<BasicAccount> accountOptional = basicAccountRepository.findById(accountId);
        if (accountOptional.isEmpty()) {
            throw new BadRequestException("The account introduced doesn't exist");
        }
        return accountOptional.get();
    }

    public void usernameAndPasswordCorrespondsAccount(Long accountId, AccountHolderDto accountHolderDto) {
        BasicAccount myAccount = accountExists(accountId);
        if (!myAccount.getPrimaryOwner().getUsername().equalsIgnoreCase(accountHolderDto.getUsername())
                || !myAccount.getPrimaryOwner().getPassword().equalsIgnoreCase(accountHolderDto.getPassword())) {
            throw new UnauthorizedException("Account ID " + accountId + " and entered credentials don't fit.");
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
