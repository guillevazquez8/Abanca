package com.proyecto.abanca.service.account;

import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.exceptions.WrongAccountException;
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

@Service
@RequiredArgsConstructor
public class CheckingService {

    private final CheckingRepository checkingRepository;

    private final AccountHoldersService accountHoldersService;

    private final StudentCheckingService studentCheckingService;
    public List<Checking> findAll() {
        return checkingRepository.findAll();
    }

    public Optional<Checking> findByIdOptional(Long id) {
        return checkingRepository.findById(id);
    }

    public Checking findById(Long id) {
        if (checkingRepository.findById(id).isEmpty()) {
            throw new BadRequestException("The introduced account doesn't exist");
        }
        return checkingRepository.findById(id).get();
    }

    public Checking save(CheckingDto checkingDto) throws WrongAccountException {
        AccountHolders primaryOwner = accountHoldersService.findById(Long.valueOf(checkingDto.getPrimaryOwnerId()));
        if (primaryOwner.getDateOfBirth().isAfter(LocalDate.of(LocalDate.now().getYear() - 24, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))) {
            studentCheckingService.save(checkingDto);
            throw new WrongAccountException("You're too young to have a Checking account! We'll create a Student Checking account instead");
        }
        Checking checking = new Checking();
        checking.setBalance(new Money(BigDecimal.valueOf(checkingDto.getBalance())));
        checking.setPrimaryOwner(primaryOwner);
        checking.setCreationDate(LocalDate.now());
        checking.setSecretKey(checkingDto.getSecretKey());
        checking.setStatus(Status.ACTIVE);

        return checkingRepository.save(checking);
    }


}