package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.dto.AccountDto;
import com.proyecto.abanca.exceptions.WrongAccountException;
import com.proyecto.abanca.model.account.StudentChecking;
import com.proyecto.abanca.service.account.StudentCheckingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abanca/student-checking")
@RequiredArgsConstructor
public class StudentCheckingController {

    private final StudentCheckingService studentCheckingService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentChecking> findAll() {return studentCheckingService.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ACCOUNTHOLDER') or hasRole('ADMIN')")
    public StudentChecking createStudentChecking(@RequestBody AccountDto accountDto) throws WrongAccountException {
        return studentCheckingService.save(accountDto);
    }
}
