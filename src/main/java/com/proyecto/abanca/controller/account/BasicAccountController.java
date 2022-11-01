package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.service.account.BasicAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basic_account")
public class BasicAccountController {

    private final BasicAccountService basicAccountService;

    @GetMapping("/my_account/{id}/{username}/{password}")
    @PreAuthorize("hasRole('ACCOUNTHOLDER')")
    public BasicAccount accessMyAccounts(@PathVariable Long id,
                                         @PathVariable String username,
                                         @PathVariable String password) {
        return basicAccountService.accessMyAccount(id, username, password);
    }

    @GetMapping("/my_balance/{id}/{username}/{password}")
    @PreAuthorize("hasRole('ACCOUNTHOLDER')")
    public Money accessMyBalance(@PathVariable Long  id,
                                 @PathVariable String username,
                                 @PathVariable String password) {
        return basicAccountService.accessMyAccountBalance(id, username, password);
    }

    @GetMapping("/any_account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Money accessAnyBalance(@PathVariable Long id) {
        return basicAccountService.accessAnyAccountBalance(id);
    }

    @PatchMapping("/any_account/{id}/{balance}")
    @PreAuthorize("hasRole('ADMIN')")
    public Money updateAnyBalance(@PathVariable Long id,
                                  @PathVariable Long balance) {
        return basicAccountService.modifyAnyAccountBalance(id, balance);
    }

}
