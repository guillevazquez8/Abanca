package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.CreditCardDto;
import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.model.account.*;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.repositories.account.CreditCardRepository;
import com.proyecto.abanca.service.user.AccountHoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    private final AccountHoldersService accountHoldersService;

    public List<CreditCard> findAll() {return creditCardRepository.findAll();}

    public CreditCard findById(Long id) {return creditCardRepository.findById(id).get();}

    public Optional<CreditCard> findByIdOptional(Long id) {return creditCardRepository.findById(id);}

    public CreditCard save(CreditCardDto creditCardDto) {
        AccountHolders primaryOwner = accountHoldersService.findById(Long.valueOf(creditCardDto.getPrimaryOwnerId()));

        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(new Money(BigDecimal.valueOf(creditCardDto.getBalance())));
        creditCard.setPrimaryOwner(primaryOwner);
        creditCard.setCreationDate(LocalDate.now());
        creditCard.setSecretKey(creditCardDto.getSecretKey());
        creditCard.setStatus(Status.ACTIVE);

        return creditCardRepository.save(creditCard);
    }

}
