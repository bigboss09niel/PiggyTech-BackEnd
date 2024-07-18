package com.example.piggytech.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.UserAuth;


public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByEmail(String email);
    UserAuth findByUsername(String username);
    Optional<UserAuth> findByUsernameOrEmail(String email, String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

}
