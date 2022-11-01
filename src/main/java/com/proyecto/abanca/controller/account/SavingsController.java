package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.dto.SavingsDto;
import com.proyecto.abanca.exceptions.WrongAccountException;
import com.proyecto.abanca.model.account.Savings;
import com.proyecto.abanca.service.account.SavingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/abanca/savings")
@RequiredArgsConstructor
public class SavingsController {

    private final SavingsService savingsService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Savings> findAllSavings() {return savingsService.findAll();}

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ACCOUNTHOLDER')")
    public Savings findById(@PathVariable Long id) {return savingsService.findById(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('ACCOUNTHOLDER')")
    public Savings createSavings(@RequestBody @Valid SavingsDto savingsDto) {
            return savingsService.save(savingsDto);
    }

}
