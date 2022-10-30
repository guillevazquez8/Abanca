package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.service.account.BasicAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basic_account")
public class BasicAccountController {

    private final BasicAccountService basicAccountService;

    @GetMapping("/my_account/{username}/{password}")
    @PreAuthorize("hasRole('ACCOUNTHOLDER')")
    public List<BasicAccount> accessMyAccounts(@PathVariable String username,
                                               @PathVariable String password) {
        return basicAccountService.accessMyAccount(username, password);
    }

}
