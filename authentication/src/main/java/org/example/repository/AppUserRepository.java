package org.example.repository;

import org.example.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {


    @Query("SELECT user FROM AppUser  user WHERE user.username=?1 OR user.email=?1")
    Optional<AppUser> findAppUserByUsernameOrEmail(String username);
}
