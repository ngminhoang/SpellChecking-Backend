package rest.spellchecking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rest.spellchecking.domain.Account;

import java.util.Optional;

@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a WHERE a.mail = :mail")
    Optional<Account> findByMail(String mail);
}
