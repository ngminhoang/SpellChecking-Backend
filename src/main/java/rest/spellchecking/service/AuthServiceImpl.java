package rest.spellchecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rest.spellchecking.authentication.AuthenticationRequest;
import rest.spellchecking.authentication.AuthenticationResponse;
import rest.spellchecking.authentication.Register;
import rest.spellchecking.configuration.JwtService;
import rest.spellchecking.domain.Account;
import rest.spellchecking.enumVariable.Role;
import rest.spellchecking.repository.AccountRepository;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthenticationResponse login(AuthenticationRequest request) {
        Optional<Account> optionalUser = accountRepository.findByMail(request.getMail());
        if (!optionalUser.isPresent()) {
            return null;
        }
        Account user = optionalUser.get();
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String jwt = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwt).build();
        }
        return null;
    }

    public AuthenticationResponse register(Register request) {
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Account user = Account.builder()
                .name(request.getName())
                .mail(request.getMail())
                .password(encryptedPassword)
                .role(Role.ROLE_USER)
                .build();
        accountRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
