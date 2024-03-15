package rest.spellchecking02.controller.controllerInterface;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.dto.HistoryDTOrs;
import rest.spellchecking02.domain.History;

import java.util.List;

@RequestMapping("/api/user/history")
@PreAuthorize("hasRole('ROLE_USER')")
public interface HistoryControllerInterface {

    @GetMapping("/search")
    List<HistoryDTOrs> search(
            @AuthenticationPrincipal Account user,
            @RequestParam("key") String key,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("soft") int soft
    );

    @GetMapping("/search/total")
    int getTotalPagesForSearch(
            @AuthenticationPrincipal Account user,
            @RequestParam("key") String key,
            @RequestParam("size") int size
    );

    @GetMapping("/page")
    List<HistoryDTOrs> getAllByPage(
            @AuthenticationPrincipal Account user,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("soft") int soft
    );

    @GetMapping("/page/total")
    int getTotalPagesForAccountId(
            @AuthenticationPrincipal Account user,
            @RequestParam("size") int size
    );

    @PostMapping("")
    History create(
            @AuthenticationPrincipal Account user,
            @RequestBody History history
    );

    @DeleteMapping("")
    boolean delete(
            @AuthenticationPrincipal Account user,
            @RequestBody Long id
    );
}
