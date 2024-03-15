package rest.spellchecking02.service.serviceInterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import rest.spellchecking02.domain.Account;
import rest.spellchecking02.domain.History;

import java.util.List;
import java.util.Optional;

public interface HistoryServiceInterface {
    Optional<History> read(Long id);

    Page<History> getAllByPageForSearch(Long id, String keyword, int size, int page, int soft);

    int getTotalPagesForSearch(Long accountId, String keyword, int pageSize);

    Page<History> getAllByPageForUser(Long accountId, int size, int page, int soft);

    int getTotalPagesForAccountId(Long accountId, int pageSize);

    List<History> all();

    History create(Long accountId, History data);

    boolean delete(Long accountId, Long id);

    boolean update(History data);
}
