package com.proyecto.abanca.controller;

import com.proyecto.abanca.service.InitMasterData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//created develop branch to work on project

@RestController
@RequestMapping("/abanca/initData")
@RequiredArgsConstructor
public class InitMasterDataController {

    private final InitMasterData initMasterData;

    @GetMapping
    public void initData() {
        this.initMasterData.initData();
    }

}
