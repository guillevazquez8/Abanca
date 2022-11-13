package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.SavingsDto;
import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Savings;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.repositories.account.SavingsRepository;
import com.proyecto.abanca.service.user.AccountHoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavingsService {

    private final SavingsRepository savingsRepository;
    private final AccountHoldersService accountHoldersService;

    public List<Savings> findAll() {return savingsRepository.findAll();}

    public Savings findById(Long id) {return savingsRepository.findById(id).get();}

    public Optional<Savings> findByIdOptional(Long id) {return savingsRepository.findById(id);}


    public Savings save(SavingsDto savingsDto) {
        AccountHolders primaryOwner = accountHoldersService.findById(Long.valueOf(savingsDto.getPrimaryOwnerId()));

        Savings savings = new Savings();
        savings.setBalance(new Money(BigDecimal.valueOf(savingsDto.getBalance())));
        savings.setPrimaryOwner(primaryOwner);
        savings.setCreationDate(LocalDate.now());
        savings.setMinimumBalance(new Money(BigDecimal.valueOf(savingsDto.getMinimumBalance())));
        savings.setInterestRate(BigDecimal.valueOf(savingsDto.getInterestRate()));

        return savingsRepository.save(savings);
    }

}
