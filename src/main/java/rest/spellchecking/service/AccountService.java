package rest.spellchecking.service;

import rest.spellchecking.domain.Account;
import rest.spellchecking.dto.AccountDTOrq;
import rest.spellchecking.dto.AccountDTOrs;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getById(Long id);

    List<Account> all();

    AccountDTOrs create(Account data);

    boolean delete(Long id);

    AccountDTOrs update(Long id, AccountDTOrq data);

}
