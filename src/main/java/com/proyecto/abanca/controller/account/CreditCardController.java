package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.dto.CreditCardDto;
import com.proyecto.abanca.model.account.CreditCard;
import com.proyecto.abanca.service.account.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/abanca/creditcard")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CreditCard> findAllCreditCards() {return creditCardService.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('ACCOUNTHOLDER')")
    public CreditCard createCreditCard(@RequestBody @Valid CreditCardDto creditCardDto) {
        return creditCardService.save(creditCardDto);
    }

}
