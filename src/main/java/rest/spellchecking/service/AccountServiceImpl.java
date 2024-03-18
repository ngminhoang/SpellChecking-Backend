package rest.spellchecking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rest.spellchecking.dto.AccountDTOrq;
import rest.spellchecking.domain.Account;
import rest.spellchecking.dto.AccountDTOrs;
import rest.spellchecking.dto.Mapper;
import rest.spellchecking.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> all() {
        return accountRepository.findAll();
    }

    public AccountDTOrs create(Account data) {
        Account account= accountRepository.save(data);
        return Mapper.fromAccountToAccountDTOrs(account);
    }

    public boolean delete(Long id) {
        if (!accountRepository.existsById(id)) {
            return false;
        }
        accountRepository.deleteById(id);
        return true;
    }

    public AccountDTOrs update(Long id, AccountDTOrq data) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (!optionalAccount.isPresent()) {
            return null;
        }
        Account existingAccount = optionalAccount.get();
        updateAccountFields(existingAccount, data);
        Account account= accountRepository.save(existingAccount);
        return Mapper.fromAccountToAccountDTOrs(account);
    }

    private void updateAccountFields(Account account, AccountDTOrq data) {
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
