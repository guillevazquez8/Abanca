package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.dto.CheckingDto;
import com.proyecto.abanca.exceptions.WrongAccountException;
import com.proyecto.abanca.model.account.StudentChecking;
import com.proyecto.abanca.service.account.StudentCheckingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentChecking")
@RequiredArgsConstructor
public class StudentCheckingController {

    private final StudentCheckingService studentCheckingService;

    @GetMapping
    public List<StudentChecking> findAll() {return studentCheckingService.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking createStudentChecking(CheckingDto checkingDto) throws WrongAccountException {
        return studentCheckingService.save(checkingDto);
    }
}
