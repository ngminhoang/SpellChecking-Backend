package rest.spellchecking02.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest.spellchecking02.authentication.AuthenticationRequest;
import rest.spellchecking02.authentication.AuthenticationResponse;
import rest.spellchecking02.authentication.Register;
import rest.spellchecking02.controller.controllerInterface.AuthControllerInterface;
import rest.spellchecking02.service.service.AuthService;
import rest.spellchecking02.service.serviceInterface.AuthServiceInterface;

@RestController
public class AuthController implements AuthControllerInterface {
    @Autowired
    private AuthServiceInterface service;

    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse res = service.login(request);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Register request
    ) {
        AuthenticationResponse res = service.register(request);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
