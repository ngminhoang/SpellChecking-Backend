package rest.spellchecking02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTOrs {
    private Long id;
    private String date;
    private String title;
    private String link;
    private String param;

}
