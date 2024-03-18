package rest.spellchecking.service;

import rest.spellchecking.authentication.PageData;
import rest.spellchecking.authentication.Sort;
import rest.spellchecking.domain.History;
import rest.spellchecking.dto.HistoryDTOrs;

import java.util.List;
import java.util.Optional;

public interface HistoryService {
    Optional<History> getById(Long id);

    PageData getAllByPageForSearch(Long id, Sort input);

    List<History> all();

    HistoryDTOrs create(Long accountId, History data);

    boolean delete(Long accountId, Long id);

    HistoryDTOrs update(History data);
}
