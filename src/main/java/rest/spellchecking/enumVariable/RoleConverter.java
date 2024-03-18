package rest.spellchecking.enumVariable;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        switch (role) {
            case ROLE_USER:
                return 1;
            case ROLE_ADMIN:
                return 2;
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }

    @Override
    public Role convertToEntityAttribute(Integer dbValue) {
        if (dbValue == null) {
            return null;
        }
        switch (dbValue) {
            case 1:
                return Role.ROLE_USER;
            case 2:
                return Role.ROLE_ADMIN;
            default:
                throw new IllegalArgumentException("Unsupported role value: " + dbValue);
        }
    }
}

