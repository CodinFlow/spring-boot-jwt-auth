package com.tericcabrel.authapi.csrf;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class CsrfController {

    @GetMapping("/auth/csrf")
    public CsrfToken csrf(CsrfToken csrf_token) {
        return csrf_token;
    }
}
