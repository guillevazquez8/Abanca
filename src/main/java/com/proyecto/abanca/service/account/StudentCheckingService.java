package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.CheckingDto;
import com.proyecto.abanca.exceptions.BadRequestException;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentCheckingService {

    private final StudentCheckingRepository studentCheckingRepository;
    private final AccountHoldersService accountHoldersService;

    public List<StudentChecking> findAll() {return studentCheckingRepository.findAll();}

    public StudentChecking save(CheckingDto checkingDto) throws WrongAccountException {
        Optional<AccountHolders> primaryOwner = accountHoldersService.findById(Long.valueOf(CheckingDto.getPrimaryOwnerId()));
        if (primaryOwner.isEmpty()) {
            throw new BadRequestException("The entered account doesn't have a Primary Owner");
        }
        if (primaryOwner.get().getDateOfBirth().isBefore(LocalDate.of(LocalDate.now().getYear() - 24, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))) {
            throw new WrongAccountException("You're too old to have a Student Checking account! We'll create a Checking account instead");
        }
        StudentChecking studentChecking = new StudentChecking();
        studentChecking.setBalance(new Money(BigDecimal.valueOf(CheckingDto.getBalance())));
        studentChecking.setPrimaryOwner(primaryOwner.get());
        studentChecking.setCreationDate(LocalDate.now());
        studentChecking.setSecretKey(CheckingDto.getSecretKey());
        studentChecking.setStatus(Status.ACTIVE);

        return studentCheckingRepository.save(studentChecking);
    }
}
