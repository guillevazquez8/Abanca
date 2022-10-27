package com.proyecto.abanca.controller.user;

import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.service.user.AccountHoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accountHolder")
public class AccountHoldersController {

    private final AccountHoldersService accountHoldersService;

    @GetMapping
    public List<AccountHolders> allAccountHolders() {return accountHoldersService.findAll();}
}
