package rest.spellchecking02.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rest.spellchecking02.dto.AccountDTOrq;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.repository.AccountRepository;
import rest.spellchecking02.service.serviceInterface.AccountServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Account> read(Long id) {
        return repository.findById(id);
    }

    public List<Account> all() {
        return repository.findAll();
    }

    public Account create(Account data) {
        return repository.save(data);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean update(Long id, AccountDTOrq data) {
        Optional<Account> optionalAccount = repository.findById(id);
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();
            updateAccountFields(existingAccount, data);
            repository.save(existingAccount);
            return true;
        }
        return false;
    }

    public void updateAccountFields(Account account, AccountDTOrq data) {
        if (data.getPassword() != null && !data.getPassword().isEmpty()) {
            account.setPassword(passwordEncoder.encode(data.getPassword()));
        }
        if (data.getMail() != null && !data.getMail().isEmpty()) {
            account.setMail(data.getMail());
        }
        if (data.getName() != null && !data.getName().isEmpty()) {
            account.setName(data.getName());
        }
    }
}
