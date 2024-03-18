package rest.spellchecking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking.dto.AccountDTOrs;
import rest.spellchecking.dto.AccountDTOrq;
import rest.spellchecking.domain.Account;
import rest.spellchecking.dto.Mapper;
import rest.spellchecking.service.AccountService;

import java.util.Optional;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private AccountService userService;

    public ResponseEntity<AccountDTOrs> getInfo(@AuthenticationPrincipal Account user) {
        Optional<Account> data = userService.getById(user.getId());
        return data.map(account -> ResponseEntity.ok(Mapper.fromAccountToAccountDTOrs(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<AccountDTOrs> updateInfo(@RequestBody AccountDTOrq userUpdate, @AuthenticationPrincipal Account user) {
        Optional<Account> data = userService.getById(user.getId());
        return data.map(account -> ResponseEntity.ok(userService.update(account.getId(), userUpdate)))
                .orElse(ResponseEntity.notFound().build());
    }
}
