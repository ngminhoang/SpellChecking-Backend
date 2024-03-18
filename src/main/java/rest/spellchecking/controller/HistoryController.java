package rest.spellchecking.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking.authentication.PageData;
import rest.spellchecking.domain.Account;
import rest.spellchecking.dto.HistoryDTOrs;
import rest.spellchecking.domain.History;

import java.util.List;

@RequestMapping("/api/user/history")
@PreAuthorize("hasRole('ROLE_USER')")
public interface HistoryController {

    @GetMapping("/search")
    PageData search(
            @AuthenticationPrincipal Account user,
            @RequestParam("key") String key,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("sort") int sort
    );


    @PostMapping("")
    HistoryDTOrs create(
            @AuthenticationPrincipal Account user,
            @RequestBody History history
    );

    @DeleteMapping("")
    boolean delete(
            @AuthenticationPrincipal Account user,
            @RequestBody Long id
    );
}
