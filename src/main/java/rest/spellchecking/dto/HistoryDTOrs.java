package rest.spellchecking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryDTOrs {
    private Long id;
    private LocalDateTime createdDate;
    private String title;
    private String link;
    private String param;

}
