package org.example.controller;

import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDataDto;
import org.example.dto.UserRegistrationDto;
import org.example.service.UserRegistrationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserRegistrationService registrationService;

    @PostMapping("/join")

    public UserRegistrationDto registerUser(@RequestBody UserDataDto userDataDto){
        return registrationService.registerUser(userDataDto);
    }
    @PostMapping("/login")
    public String loginUser(Authentication authentication){
        return registrationService.loginUser(authentication);
    }
}
