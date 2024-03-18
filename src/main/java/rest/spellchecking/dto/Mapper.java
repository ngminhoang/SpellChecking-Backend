package rest.spellchecking.dto;

import org.springframework.stereotype.Component;
import rest.spellchecking.domain.Account;
import rest.spellchecking.domain.History;

public class Mapper {
    public static Account fromAccountDTOrqToAccount(AccountDTOrq input) {
        return Account.builder()
                .mail(input.getMail())
                .name(input.getName())
                .password(input.getPassword())
                .build();
    }

    public static AccountDTOrs fromAccountToAccountDTOrs(Account input) {
        return AccountDTOrs.builder()
                .mail(input.getMail())
                .name(input.getName())
                .build();
    }

    public static HistoryDTOrs fromHistoryToHistoryDTOrs(History input) {
        return HistoryDTOrs.builder()
                .id(input.getId())
                .link(input.getLink())
                .createdDate(input.getCreatedDate())
                .param(input.getParam())
                .title(input.getTitle())
                .build();
    }
}
