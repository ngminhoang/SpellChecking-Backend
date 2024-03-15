package rest.spellchecking02.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.service.service.AccountService;
import rest.spellchecking02.service.serviceInterface.AccountServiceInterface;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AccountServiceInterface service;

    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = service.all();
        return ResponseEntity.ok(accounts);
    }

    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        service.create(account);
        return ResponseEntity.ok("Account added successfully");
    }
}
