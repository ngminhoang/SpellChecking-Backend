package rest.spellchecking02.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking02.controller.controllerInterface.HistoryControllerInterface;
import rest.spellchecking02.dto.HistoryDTOrs;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.domain.History;
import rest.spellchecking02.dto.Mapper;
import rest.spellchecking02.service.service.HistoryService;
import rest.spellchecking02.service.serviceInterface.HistoryServiceInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HistoryController implements HistoryControllerInterface {

    @Autowired
    private HistoryServiceInterface service;

    @Autowired
    private Mapper mapper;

    @Override
    public List<HistoryDTOrs> search(
            @AuthenticationPrincipal Account user,
            @RequestParam("key") String key,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("soft") int soft
    ) {
        Long accountId = user.getId();
        List<History> histories = service.getAllByPageForSearch(accountId, key, size, page, soft).getContent();
        return histories.stream()
                .map(history -> mapper.fromHistoryToHistoryDTOrs(history))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalPagesForSearch(
            @AuthenticationPrincipal Account user,
            @RequestParam("key") String key,
            @RequestParam("size") int size
    ) {
        Long accountId = user.getId();
        return service.getTotalPagesForSearch(accountId, key, size);
    }

    @Override
    public List<HistoryDTOrs> getAllByPage(
            @AuthenticationPrincipal Account user,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("soft") int soft
    ) {
        Long accountId = user.getId();
        List<History> histories = service.getAllByPageForUser(accountId, size, page, soft).getContent();
        return histories.stream()
                .map(history -> mapper.fromHistoryToHistoryDTOrs(history))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalPagesForAccountId(
            @AuthenticationPrincipal Account user,
            @RequestParam("size") int size
    ) {
        Long accountId = user.getId();
        return service.getTotalPagesForAccountId(accountId, size);
    }

    @Override
    @PostMapping("")
    public History create(
            @AuthenticationPrincipal Account user,
            @RequestBody History history
    ) {
        return service.create(user.getId(), history);
    }

    @Override
    public boolean delete(
            @AuthenticationPrincipal Account user,
            @RequestBody Long id
    ) {
        return service.delete(user.getId(), id);
    }
}

