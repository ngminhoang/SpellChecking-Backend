package rest.spellchecking02.controller.controllerInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking02.domain.Account;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public interface AdminControllerInterface {

    @GetMapping("/account")
    ResponseEntity<List<Account>> getAllAccounts();

    @PostMapping("/account")
    ResponseEntity<String> createAccount(@RequestBody Account account);
}
