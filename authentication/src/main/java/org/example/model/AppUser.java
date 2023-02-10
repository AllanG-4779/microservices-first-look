package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String firstname;
    private String lastname;

    private String roles;
    @Column(nullable = false)
    private String password;
    private Boolean credentialsExpired = false;
    private Boolean accountDisabled =  false;
    private Boolean isActive = false;

}
