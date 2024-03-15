package rest.spellchecking02.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking02.controller.controllerInterface.UserControllerInterface;
import rest.spellchecking02.dto.AccountDTOrs;
import rest.spellchecking02.dto.AccountDTOrq;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.dto.Mapper;
import rest.spellchecking02.service.service.AccountService;
import rest.spellchecking02.service.serviceInterface.AccountServiceInterface;

import java.util.Optional;

@RestController
public class UserController implements UserControllerInterface {
    @Autowired
    private AccountServiceInterface service;
    @Autowired
    private Mapper mapper;

    public ResponseEntity<AccountDTOrs> getInfo(@AuthenticationPrincipal Account user) {
        Optional<Account> data = service.read(user.getId());
        return data.map(account -> ResponseEntity.ok(mapper.fromAccountToAccountDTOrs(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Boolean> updateInfo(@RequestBody AccountDTOrq userUpdate, @AuthenticationPrincipal Account user) {
        Optional<Account> data = service.read(user.getId());
        return data.map(account -> ResponseEntity.ok(service.update(account.getId(), userUpdate)))
                .orElse(ResponseEntity.notFound().build());
    }
}
