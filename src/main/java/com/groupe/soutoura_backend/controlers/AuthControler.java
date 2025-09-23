package com.groupe.soutoura_backend.controlers;



import com.groupe.soutoura_backend.models.UserDetailsImpl;
import com.groupe.soutoura_backend.securities.JwtUtils;
import com.groupe.soutoura_backend.securities.JwtUtils;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthControler {

    private final AuthenticationManager authManager;

    private final JwtUtils jwtUtils;

    public AuthControler(AuthenticationManager authManager, JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.motDePasse)
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String token = jwtUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    public static record LoginRequest(String email, String motDePasse) {}
    public static record JwtResponse(String token, String email, Object roles) {}
}

