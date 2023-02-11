package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.LoginRequestDto;
import org.example.dto.UserDataDto;
import org.example.dto.UserRegistrationDto;
import org.example.model.AppUser;
import org.example.repository.AppUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserRegistrationDto registerUser(UserDataDto userDataDto) {
        AppUser appUser = AppUser.builder()
                .firstname(userDataDto.getFirstname())
                .lastname(userDataDto.getLastname())
                .username(userDataDto.getUsername())
                .password(passwordEncoder.encode(userDataDto.getPassword()))
                .email(userDataDto.getEmail())
                .roles("USER")
                .build();
        appUserRepository.save(appUser);
        return  UserRegistrationDto.builder()
                .message("User created successfully")
                .localDateTime(LocalDateTime.now())
                .email(userDataDto.getEmail())
                .username(userDataDto.getUsername())
                .build();
    }

    @Override
    public String loginUser(LoginRequestDto loginRequestDto) {
      Authentication authentication =  authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername()
                       , loginRequestDto.getPassword())
       );

       return generateToken(authentication);
    }

    @Override
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .claim("scope", scope)
                .build();


        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
