package com.proyecto.abanca.controller.account;

import org.springframework.web.bind.annotation.RestController;
import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.dto.CheckingDto;
import com.proyecto.abanca.service.account.CheckingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checking")
@RequiredArgsConstructor
public class CheckingController {

    private final CheckingService checkingService;

    @GetMapping
    public List<Checking> findAllCheckings() {
        return checkingService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createChecking(@RequestBody CheckingDto checkingDto) {
        return checkingService.save(checkingDto);
    }

}
