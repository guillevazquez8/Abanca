package com.proyecto.abanca.security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/abanca/test")
public class TestController {

    @GetMapping
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/accountholder")
    @PreAuthorize("hasRole('ACCOUNTHOLDER') or hasRole('THIRDPARTY') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/thirdparty")
    @PreAuthorize("hasRole('THIRDPARTY')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
