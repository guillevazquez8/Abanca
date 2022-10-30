package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.dto.SavingsDto;
import com.proyecto.abanca.exceptions.WrongAccountException;
import com.proyecto.abanca.model.account.Savings;
import com.proyecto.abanca.service.account.SavingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/savings")
@RequiredArgsConstructor
public class SavingsController {

    private final SavingsService savingsService;

    @GetMapping
    public List<Savings> findAllSavings() {return savingsService.findAll();}

    @GetMapping
    public Savings findById(Long id) {return savingsService.findById(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavings(@RequestBody @Valid SavingsDto savingsDto) {
            return savingsService.save(savingsDto);
    }

}
