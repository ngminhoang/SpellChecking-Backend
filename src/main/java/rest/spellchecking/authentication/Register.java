package rest.spellchecking.authentication;

import lombok.Builder;
import lombok.Data;
import rest.spellchecking.enumVariable.Role;
@Builder
@Data
public class Register {
    private String mail;
    private String password;
    private String name;
    private Role role;
}
