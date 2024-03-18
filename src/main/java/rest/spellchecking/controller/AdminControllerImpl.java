package rest.spellchecking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking.domain.Account;
import rest.spellchecking.service.AccountService;

import java.util.List;

@RestController
public class AdminControllerImpl {
    @Autowired
    private AccountService accountService;

    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.all();
        return ResponseEntity.ok(accounts);
    }

    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        accountService.create(account);
        return ResponseEntity.ok("Account added successfully");
    }
}
