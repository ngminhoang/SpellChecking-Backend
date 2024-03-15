package rest.spellchecking02.authentication;

import lombok.Builder;
import lombok.Data;
import rest.spellchecking02.domain.Erole;
@Builder
@Data
public class Register {
    private String mail;
    private String password;
    private String name;
    private Erole role;
}
