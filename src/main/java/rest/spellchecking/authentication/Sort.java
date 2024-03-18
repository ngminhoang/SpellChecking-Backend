package rest.spellchecking.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Sort {
    private int size;
    private int page;
    private int sort;
    private String key;
}
