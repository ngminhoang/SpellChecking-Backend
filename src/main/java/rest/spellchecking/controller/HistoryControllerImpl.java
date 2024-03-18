package rest.spellchecking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rest.spellchecking.authentication.PageData;
import rest.spellchecking.authentication.Sort;
import rest.spellchecking.dto.HistoryDTOrs;
import rest.spellchecking.domain.Account;
import rest.spellchecking.domain.History;
import rest.spellchecking.dto.Mapper;
import rest.spellchecking.service.HistoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HistoryControllerImpl implements HistoryController {

    @Autowired
    private HistoryService historyService;


    @Override
    public PageData search(
            @AuthenticationPrincipal Account user,
            @RequestParam("key") String key,
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @RequestParam("sort") int sort
    ) {
        Long accountId = user.getId();
        Sort input = new Sort(size, page, sort, key);
        PageData histories = historyService.getAllByPageForSearch(accountId, input);
        return histories;
    }


    @Override
    public HistoryDTOrs create(
            @AuthenticationPrincipal Account user,
            @RequestBody History history
    ) {
        return historyService.create(user.getId(), history);
    }

    @Override
    public boolean delete(
            @AuthenticationPrincipal Account user,
            @RequestBody Long id
    ) {
        return historyService.delete(user.getId(), id);
    }
}

