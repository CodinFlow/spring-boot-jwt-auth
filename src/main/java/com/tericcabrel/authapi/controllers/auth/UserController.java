package com.tericcabrel.authapi.controllers.auth;

import com.tericcabrel.authapi.entities.user.User;
import com.tericcabrel.authapi.services.JwtService;
import com.tericcabrel.authapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://web-application-development7405251-317d6980115c9bfc5d610a5bb25d.gitlab.io/")

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    private JwtService jwtService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    //TODO: Implement the delete user endpoint (SETTINGS ENTITY)
    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        userService.deleteUserAccount(username);
        return ResponseEntity.ok("User deleted successfully");

    }
}
