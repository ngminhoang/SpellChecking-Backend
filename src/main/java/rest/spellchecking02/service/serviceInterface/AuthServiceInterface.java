package rest.spellchecking02.service.serviceInterface;

import rest.spellchecking02.authentication.AuthenticationRequest;
import rest.spellchecking02.authentication.AuthenticationResponse;
import rest.spellchecking02.authentication.Register;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.domain.Erole;

import java.util.Optional;

public interface AuthServiceInterface {
    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse register(Register request);
}
