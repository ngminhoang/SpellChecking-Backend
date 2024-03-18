package rest.spellchecking.service;

import rest.spellchecking.authentication.AuthenticationRequest;
import rest.spellchecking.authentication.AuthenticationResponse;
import rest.spellchecking.authentication.Register;

public interface AuthService {
    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse register(Register request);
}
