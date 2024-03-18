package rest.spellchecking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking.domain.Account;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public interface AdminController {

    @GetMapping("/account")
    ResponseEntity<List<Account>> getAllAccounts();

    @PostMapping("/account")
    ResponseEntity<String> createAccount(@RequestBody Account account);
}
