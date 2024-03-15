package rest.spellchecking02.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.domain.History;


@Component
public class Mapper {
    public Account fromAccountDTOrqToAccount(AccountDTOrq input) {
        Account result = new Account();
        result.setMail(input.getMail());
        result.setName(input.getName());
        result.setPassword(input.getPassword());
        return result;
    }

    public AccountDTOrs fromAccountToAccountDTOrs(Account input) {
        AccountDTOrs result = new AccountDTOrs();
        result.setMail(input.getMail());
        result.setName(input.getName());
        return result;
    }

    public HistoryDTOrs fromHistoryToHistoryDTOrs(History input) {
        HistoryDTOrs result = new HistoryDTOrs();
        result.setId(input.getId());
        result.setLink(input.getLink());
        result.setDate(input.getDate());
        result.setParam(input.getParam());
        result.setTitle(input.getTitle());
        return result;
    }

}
