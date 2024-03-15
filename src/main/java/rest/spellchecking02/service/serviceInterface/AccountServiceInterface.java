package rest.spellchecking02.service.serviceInterface;

import rest.spellchecking02.domain.Account;
import rest.spellchecking02.dto.AccountDTOrq;

import java.util.List;
import java.util.Optional;

public interface AccountServiceInterface {
    Optional<Account> read(Long id);

    List<Account> all();

    Account create(Account data);

    boolean delete(Long id);

    boolean update(Long id, AccountDTOrq data);

    void updateAccountFields(Account account, AccountDTOrq data);
}
