package com.tericcabrel.authapi.controllers.auth;

import com.tericcabrel.authapi.dtos.LoginUserDto;
import com.tericcabrel.authapi.dtos.SignupUserDto;
import com.tericcabrel.authapi.entities.user.User;
import com.tericcabrel.authapi.responses.LoginResponse;
import com.tericcabrel.authapi.services.AuthenticationService;
import com.tericcabrel.authapi.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://web-application-development7405251-317d6980115c9bfc5d610a5bb25d.gitlab.io/")
@RequestMapping("/auth")
@org.springframework.web.bind.annotation.RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupUserDto registerUserDto) {
        authenticationService.signup(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }



    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        String tokenWithoutBearer = token.replace("Bearer ", "");
        String username = jwtService.extractUsername(tokenWithoutBearer);
        UserDetails userDetails = authenticationService.loadUserByUsername(username);
        if (jwtService.isTokenValid(tokenWithoutBearer, userDetails)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}