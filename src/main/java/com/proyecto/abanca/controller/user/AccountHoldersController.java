package com.proyecto.abanca.controller.user;

import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.service.user.AccountHoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abanca/accountholder")
public class AccountHoldersController {

    private final AccountHoldersService accountHoldersService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountHolders> allAccountHolders() {return accountHoldersService.findAll();}

    @GetMapping("/find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ACCOUNTHOLDER')")
    public AccountHolders findById(Long id) {
        return accountHoldersService.findById(id);
    }
}
