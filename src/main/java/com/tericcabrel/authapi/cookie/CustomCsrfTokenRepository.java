package com.tericcabrel.authapi.cookie;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final String cookieName = "XSRF-TOKEN";
    private final String cookiePath = "/";
    private final boolean httpOnly = true;
    private final boolean secure = true;
    private final int cookieMaxAge = 1800; // 30 minutes
    private final String sameSite = "None"; // Set SameSite attribute to None

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        return Optional.ofNullable(WebUtils.getCookie(request, cookieName))
                .map(cookie -> new DefaultCsrfToken("X-XSRF-TOKEN", "XSRF-TOKEN", cookie.getValue()))
                .orElse(null);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String tokenValue = (token != null) ? token.getToken() : "";
        String cookieHeader = String.format(
                "%s=%s; Path=%s; HttpOnly=%b; Secure=%b; SameSite=%s; Max-Age=%d",
                cookieName, tokenValue, cookiePath, httpOnly, secure, sameSite, cookieMaxAge
        );
        response.setHeader("Set-Cookie", cookieHeader);
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        // Generate a new CSRF token
        return new DefaultCsrfToken("X-XSRF-TOKEN", "XSRF-TOKEN", UUID.randomUUID().toString());
    }

}
