package rest.spellchecking02.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="mail",unique = true, nullable = false)
    private String mail;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="name",nullable = false)
    private String name;
    //@Enumerated(EnumType.STRING)
    @Column(name="role")
    private Boolean role;
    @OneToMany
    @JoinColumn(name = "histories_list")
    private List<History> histories;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = role ? "ROLE_ADMIN" : "ROLE_USER";
        return List.of(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getUsername() {
        return getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
