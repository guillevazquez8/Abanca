package com.proyecto.abanca.controller.account;

import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.dto.CheckingDto;
import com.proyecto.abanca.service.account.CheckingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checking")
@RequiredArgsConstructor
public class CheckingController {

    private final CheckingService checkingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createChecking(@RequestBody CheckingDto checkingDto) {
        return checkingService.save(checkingDto);
    }

}
