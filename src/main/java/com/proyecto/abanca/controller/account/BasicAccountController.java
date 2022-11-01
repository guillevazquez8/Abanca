package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.dto.AccountHolderDto;
import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.service.account.BasicAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abanca/basic-account")
public class BasicAccountController {

    private final BasicAccountService basicAccountService;

    @GetMapping("/my-account/{accountId}")
    @PreAuthorize("hasRole('ACCOUNTHOLDER')")
    public BasicAccount accessMyAccounts(@PathVariable Long accountId,
                                         @RequestBody AccountHolderDto accountHolderDto) {
        return basicAccountService.accessMyAccount(accountId, accountHolderDto);
    }

    @GetMapping("/my-balance/{accountId}")
    @PreAuthorize("hasRole('ACCOUNTHOLDER')")
    public Money accessMyBalance(@PathVariable Long accountId,
                                 @RequestBody AccountHolderDto accountHolderDto) {
        return basicAccountService.accessMyAccountBalance(accountId, accountHolderDto);
    }

    @GetMapping("/any-account/{accountId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Money accessAnyBalance(@PathVariable Long accountId) {
        return basicAccountService.accessAnyAccountBalance(accountId);
    }

    @PatchMapping("/any-account/{accountId}/{balance}")
    @PreAuthorize("hasRole('ADMIN')")
    public Money updateAnyBalance(@PathVariable Long accountId,
                                  @PathVariable Long balance) {
        return basicAccountService.modifyAnyAccountBalance(accountId, balance);
    }

}
