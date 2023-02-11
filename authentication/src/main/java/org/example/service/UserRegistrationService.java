package org.example.service;

import org.example.dto.LoginRequestDto;
import org.example.dto.UserDataDto;
import org.example.dto.UserRegistrationDto;
import org.springframework.security.core.Authentication;

public interface UserRegistrationService {
     UserRegistrationDto registerUser(UserDataDto userDataDto);



     String loginUser(LoginRequestDto loginRequestDto);

     String generateToken(Authentication authentication);
}
