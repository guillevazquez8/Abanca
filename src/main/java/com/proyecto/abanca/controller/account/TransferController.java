package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.dto.TransferDto;
import com.proyecto.abanca.exceptions.NoFundsException;
import com.proyecto.abanca.model.account.Transfer;
import com.proyecto.abanca.service.account.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ACCOUNTHOLDER')")
    public Transfer transfer(@RequestBody TransferDto transferDto) throws NoFundsException {
        return transferService.transfer(transferDto);
    }

}
