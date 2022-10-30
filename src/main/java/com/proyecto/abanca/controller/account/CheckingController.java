package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.exceptions.WrongAccountException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.dto.CheckingDto;
import com.proyecto.abanca.service.account.CheckingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/checking")
@RequiredArgsConstructor
public class CheckingController {

    private final CheckingService checkingService;

    @GetMapping
    @PreAuthorize("hasRole('ACCOUNTHOLDER') or hasRole('THIRDPARTY') or hasRole('ADMIN')")
    public List<Checking> findAllCheckings() {
        return checkingService.findAll();
    }

    @GetMapping
    public Checking findById(Long id) {return checkingService.findById(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ACCOUNTHOLDER') or hasRole('THIRDPARTY') or hasRole('ADMIN')")
    public Checking createChecking(@RequestBody CheckingDto checkingDto) throws WrongAccountException {
        return checkingService.save(checkingDto);
    }

}
