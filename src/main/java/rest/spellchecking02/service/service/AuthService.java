package rest.spellchecking02.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rest.spellchecking02.authentication.AuthenticationRequest;
import rest.spellchecking02.authentication.AuthenticationResponse;
import rest.spellchecking02.authentication.Register;
import rest.spellchecking02.configuration.JwtService;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.domain.Erole;
import rest.spellchecking02.repository.AccountRepository;
import rest.spellchecking02.service.serviceInterface.AuthServiceInterface;

import java.util.Optional;

@Service
public class AuthService implements AuthServiceInterface {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthenticationResponse login(AuthenticationRequest request) {
        Optional<Account> optionalUser = repository.findByMail(request.getMail());
        if (optionalUser.isPresent()) {
            Account user = optionalUser.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String jwt = jwtService.generateToken(user);
                return AuthenticationResponse.builder().token(jwt).build();
            }
        }
        return null;
    }

    public AuthenticationResponse register(Register request) {
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Account user = Account.builder()
                .name(request.getName())
                .mail(request.getMail())
                .password(encryptedPassword)
                .role(false)
                .build();
        repository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
