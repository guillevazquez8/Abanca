package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.AccountDto;
import com.proyecto.abanca.exceptions.WrongAccountException;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Status;
import com.proyecto.abanca.model.account.StudentChecking;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.repositories.account.StudentCheckingRepository;
import com.proyecto.abanca.service.user.AccountHoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentCheckingService {

    private final StudentCheckingRepository studentCheckingRepository;
    private final AccountHoldersService accountHoldersService;

    public List<StudentChecking> findAll() {return studentCheckingRepository.findAll();}

    public StudentChecking save(AccountDto accountDto) throws WrongAccountException {
        AccountHolders primaryOwner = accountHoldersService.findById(Long.valueOf(accountDto.getPrimaryOwnerId()));
        if (primaryOwner.getDateOfBirth().isBefore(LocalDate.of(LocalDate.now().getYear() - 24, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))) {
            throw new WrongAccountException("You're too old to have a Student Checking account! You should create a Checking account instead");
        }
        StudentChecking studentChecking = new StudentChecking();
        studentChecking.setBalance(new Money(BigDecimal.valueOf(accountDto.getBalance())));
        studentChecking.setPrimaryOwner(primaryOwner);
        studentChecking.setCreationDate(LocalDate.now());
        studentChecking.setSecretKey(accountDto.getSecretKey());
        studentChecking.setStatus(Status.ACTIVE);

        return studentCheckingRepository.save(studentChecking);
    }
}
