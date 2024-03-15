package rest.spellchecking02.controller.controllerInterface;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.spellchecking02.authentication.AuthenticationRequest;
import rest.spellchecking02.authentication.AuthenticationResponse;
import rest.spellchecking02.authentication.Register;

@RequestMapping("/api")
public interface AuthControllerInterface {
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    );

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(
            @RequestBody Register request
    );
}
