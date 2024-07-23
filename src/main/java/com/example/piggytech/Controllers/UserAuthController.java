package com.example.piggytech.Controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.DTO.LoginRequest;
import com.example.piggytech.DTO.RegistrationRequest;
import com.example.piggytech.Model.Role;
import com.example.piggytech.Model.UserAuth;
import com.example.piggytech.Model.UserDetail;
import com.example.piggytech.Repository.RoleRepository;
import com.example.piggytech.Repository.UserAuthRepository;
import com.example.piggytech.Repository.UserDetailRepository;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAllUserDetails() {
        List<UserDetail> userDetails = userDetailRepository.findAll();

        List<Map<String, Object>> response = userDetails.stream().map(userDetail -> {
            UserAuth userAuth = userDetail.getUserAuth();

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("username", userAuth.getUsername());
            userMap.put("email", userAuth.getEmail());
            userMap.put("address", userDetail.getAddress());
            userMap.put("phone", userDetail.getPhone());
            userMap.put("gender", userDetail.getGender());
            userMap.put("createdAt", userDetail.getCreatedAt());
            userMap.put("roles", userAuth.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()));

            return userMap;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest){

        // Check if username exists in DB
        if(userAuthRepository.existsByUsername(registrationRequest.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Check if email exists in DB
        if(userAuthRepository.existsByEmail(registrationRequest.getEmail())){
            return new ResponseEntity<>("An account is already registered with this email", HttpStatus.BAD_REQUEST);
        }
        
        UserAuth userAuth = new UserAuth(
            registrationRequest.getUsername(),
            registrationRequest.getEmail(),
            passwordEncoder.encode(registrationRequest.getPassword())
        );

        Role role = roleRepository.findByName("ROLE_ADMIN")
            .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
        userAuth.setRoles(Collections.singleton(role));

        userAuthRepository.save(userAuth);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest){

        // Check if username exists in DB
        if(userAuthRepository.existsByUsername(registrationRequest.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Check if email exists in DB
        if(userAuthRepository.existsByEmail(registrationRequest.getEmail())){
            return new ResponseEntity<>("An account is already registered with this email", HttpStatus.BAD_REQUEST);
        }
        
        UserAuth userAuth = new UserAuth(
            registrationRequest.getUsername(),
            registrationRequest.getEmail(),
            passwordEncoder.encode(registrationRequest.getPassword())
        );

        Role role = roleRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Role USER not found"));
        userAuth.setRoles(Collections.singleton(role));

        userAuthRepository.save(userAuth);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest){
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserAuth userAuth = userAuthRepository.findByUsernameOrEmail(
            loginRequest.getUsernameOrEmail(), 
            loginRequest.getUsernameOrEmail()
        ).orElseThrow(() -> 
            new UsernameNotFoundException("User not found with username or email: " + loginRequest.getUsernameOrEmail())
        );

        UserDetail userDetail = userDetailRepository.findById(userAuth.getId())
            .orElseThrow(() -> new RuntimeException("User details not found for user ID: " + userAuth.getId()));

        Set<String> roles = userAuth.getRoles()
            .stream()
            .map(role -> role.getName())
            .collect(Collectors.toSet());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User logged in successfully");
        response.put("username", userAuth.getUsername());
        response.put("email", userAuth.getEmail());
        response.put("address", userDetail.getAddress());
        response.put("phone", userDetail.getPhone());
        response.put("gender", userDetail.getGender());
        response.put("createdAt", userDetail.getCreatedAt());
        response.put("roles", roles);

        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (BadCredentialsException e) {
        return new ResponseEntity<>(Map.of("error", "Invalid username or password"), HttpStatus.UNAUTHORIZED);
    } catch (UsernameNotFoundException e) {
        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {
        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}