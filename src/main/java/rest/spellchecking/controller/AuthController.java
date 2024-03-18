package rest.spellchecking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.spellchecking.authentication.AuthenticationRequest;
import rest.spellchecking.authentication.AuthenticationResponse;
import rest.spellchecking.authentication.Register;

@RequestMapping("/api")
public interface AuthController {
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    );

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(
            @RequestBody Register request
    );
}
