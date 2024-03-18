package rest.spellchecking.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rest.spellchecking.authentication.PageData;
import rest.spellchecking.authentication.Sort;
import rest.spellchecking.domain.Account;
import rest.spellchecking.domain.History;
import rest.spellchecking.dto.HistoryDTOrs;
import rest.spellchecking.dto.Mapper;
import rest.spellchecking.enumVariable.SortType;
import rest.spellchecking.repository.AccountRepository;
import rest.spellchecking.repository.HistoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static rest.spellchecking.enumVariable.SortType.*;

@Service
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Optional<History> getById(Long id) {
        return historyRepository.findById(id);
    }

    public PageData getAllByPageForSearch(Long id, Sort input) {
        Page<History> page = null;
        Pageable pageable = PageRequest.of(input.getPage(), input.getSize());
        SortType sortType = SortType.fromValue(input.getSort());

        switch (sortType) {
            case ORDER_BY_DATE:
                page = historyRepository.searchOrderByDate(id, input.getKey(), pageable);
                break;
            case ORDER_BY_TITLE:
                page = historyRepository.searchOrderByTitle(id, input.getKey(), pageable);
                break;
            case ORDER_BY_LINK:
                page = historyRepository.searchOrderByLink(id, input.getKey(), pageable);
                break;
        }
        if (page == null) {
            return null;
        }
        List<History> histories = page.getContent();
        List<HistoryDTOrs> historiesDTO = histories.stream()
                .map(history -> Mapper.fromHistoryToHistoryDTOrs(history))
                .collect(Collectors.toList());

        int totalPage = page.getTotalPages();
        return new PageData(historiesDTO, totalPage);
    }


    public List<History> all() {
        return historyRepository.findAll();
    }

    public HistoryDTOrs create(Long accountId, History data) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            data.setAccount(account.get());
            History history = historyRepository.save(data);
            return Mapper.fromHistoryToHistoryDTOrs(history);
        }
        return null;
    }

    public boolean delete(Long accountId, Long id) {
        History data = historyRepository.getById(id);
        if (!accountId.equals(data.getAccount().getId())) {
            return false;
        }
        historyRepository.delete(data);
        return true;
    }

    public HistoryDTOrs update(History data) {
        History updatedHistory = historyRepository.save(data);
        return Mapper.fromHistoryToHistoryDTOrs(updatedHistory);
    }
}
