package org.example.controller;

import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.example.dto.LoginRequestDto;
import org.example.dto.UserDataDto;
import org.example.dto.UserRegistrationDto;
import org.example.service.UserRegistrationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserRegistrationService registrationService;

    @PostMapping("/join")
    public UserRegistrationDto registerUser(@RequestBody UserDataDto userDataDto){
        return registrationService.registerUser(userDataDto);
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Allan";
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDto loginRequestDto){
        return registrationService.loginUser(loginRequestDto);
    }
}
