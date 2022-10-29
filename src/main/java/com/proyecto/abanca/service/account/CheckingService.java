package com.proyecto.abanca.service.account;

import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Status;
import com.proyecto.abanca.dto.CheckingDto;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.repositories.account.CheckingRepository;
import com.proyecto.abanca.service.user.AccountHoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.prefs.BackingStoreException;

@Service
@RequiredArgsConstructor
public class CheckingService {

    private final CheckingRepository checkingRepository;

    private final AccountHoldersService accountHoldersService;

    public List<Checking> findAll() {
        return checkingRepository.findAll();
    }

    public Checking save(CheckingDto checkingDto) {
        Optional<AccountHolders> primaryOwner = accountHoldersService.findById(Long.valueOf(checkingDto.getPrimaryOwnerId()));
        if (primaryOwner.isEmpty()) {
            throw new BadRequestException("La cuenta introducida no tiene Primary Owner");
        }
        Checking checking = new Checking();
        checking.setBalance(new Money(BigDecimal.valueOf(checkingDto.getBalance())));
        checking.setPrimaryOwner(primaryOwner.get());
        checking.setCreationDate(LocalDate.now());
        checking.setSecretKey(checkingDto.getSecretKey());
        checking.setStatus(Status.ACTIVE);

        return checkingRepository.save(checking);
    }
}