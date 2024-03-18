package rest.spellchecking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest.spellchecking.authentication.AuthenticationRequest;
import rest.spellchecking.authentication.AuthenticationResponse;
import rest.spellchecking.authentication.Register;
import rest.spellchecking.service.AuthService;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    private AuthService authService;

    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse res = authService.login(request);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Register request
    ) {
        AuthenticationResponse res = authService.register(request);
        if (res == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(res);
    }
}
