package rest.spellchecking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.spellchecking.domain.Account;
import rest.spellchecking.dto.AccountDTOrq;
import rest.spellchecking.dto.AccountDTOrs;

@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_USER')")
public interface UserController {
    @GetMapping("/info")
    ResponseEntity<AccountDTOrs> getInfo(@AuthenticationPrincipal Account user);

    @PutMapping("/update")
    ResponseEntity<AccountDTOrs> updateInfo(@RequestBody AccountDTOrq userUpdate, @AuthenticationPrincipal Account user);
}
