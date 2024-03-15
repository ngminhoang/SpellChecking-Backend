package rest.spellchecking02.service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.domain.History;
import rest.spellchecking02.repository.AccountRepository;
import rest.spellchecking02.repository.HistoryRepository;
import rest.spellchecking02.service.serviceInterface.HistoryServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HistoryService implements HistoryServiceInterface {

    @Autowired
    private HistoryRepository repository;

    @Autowired
    private AccountRepository accountRepo;

    public Optional<History> read(Long id) {
        return repository.findById(id);
    }

    public Page<History> getAllByPageForSearch(Long id, String keyword, int size, int page, int soft) {
        if (keyword != null) {
            Pageable pageable = PageRequest.of(page, size);
            if (soft == 3) return repository.searchOrderByDate(id, keyword, pageable);
            if (soft == 6) return repository.searchOrderByTitle(id, keyword, pageable);
            if (soft == 9) return repository.searchOrderByLink(id, keyword, pageable);
        }
        return null;
    }

    public int getTotalPagesForSearch(Long accountId, String keyword, int pageSize) {
        Page<History> firstPage = repository.searchOrderByDate(accountId, keyword, PageRequest.of(0, pageSize));
        return firstPage.getTotalPages();
    }

    public Page<History> getAllByPageForUser(Long accountId, int size, int page, int soft) {
        Pageable pageable = PageRequest.of(page, size);
        if (soft == 3) return repository.findAllByAccountIdOrderByDate(accountId, pageable);
        if (soft == 6) return repository.findAllByAccountIdOrderByTitle(accountId, pageable);
        if (soft == 9) return repository.findAllByAccountIdOrderByLink(accountId, pageable);
        return null;
    }

    public int getTotalPagesForAccountId(Long accountId, int pageSize) {
        Page<History> firstPage = repository.findAllByAccountIdOrderByDate(accountId, PageRequest.of(0, pageSize));
        return firstPage.getTotalPages();
    }

    public List<History> all() {
        return repository.findAll();
    }

    public History create(Long accountId, History data) {
        Optional<Account> account = accountRepo.findById(accountId);
        if (account.isPresent()) {
            data.setAccount(account.get());
            return repository.save(data);
        }
        return null;
    }

    public boolean delete(Long accountId, Long id) {
        History data = repository.getById(id);
        if (accountId.equals(data.getAccount().getId())) {
            repository.delete(data);
            return true;
        }
        return false;
    }

    public boolean update(History data) {
        repository.save(data);
        return true;
    }
}
