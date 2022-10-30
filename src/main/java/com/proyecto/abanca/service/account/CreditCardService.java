package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.CreditCardDto;
import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.model.account.CreditCard;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Status;
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

    public CreditCard save(CreditCardDto creditCardDto) {
        Optional<AccountHolders> primaryOwner = accountHoldersService.findById(Long.valueOf(creditCardDto.getPrimaryOwnerId()));
        if (primaryOwner.isEmpty()) {
            throw new BadRequestException("Entered account doesn't have a Primary Owner");
        }
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(new Money(BigDecimal.valueOf(creditCardDto.getBalance())));
        creditCard.setPrimaryOwner(primaryOwner.get());
        creditCard.setCreationDate(LocalDate.now());
        creditCard.setSecretKey(creditCardDto.getSecretKey());
        creditCard.setStatus(Status.ACTIVE);

        return creditCardRepository.save(creditCard);
    }

}
