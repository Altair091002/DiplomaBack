package com.example.demoauth.controllers;

import com.example.demoauth.jwt.PersonDetailsImpl;
import com.example.demoauth.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 180)
public class ClientController {
    @GetMapping("/info")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetailsImpl principal = (PersonDetailsImpl) authentication.getPrincipal();
        return principal.getUser();
    }
}
