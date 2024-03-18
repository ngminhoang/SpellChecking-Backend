package rest.spellchecking.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import rest.spellchecking.dto.HistoryDTOrs;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class PageData {
    private List<HistoryDTOrs> histories;
    private int totalPage;
}
