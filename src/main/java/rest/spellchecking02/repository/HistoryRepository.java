package rest.spellchecking02.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rest.spellchecking02.domain.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT h FROM History h WHERE (h.title LIKE %:keyword% OR h.link LIKE %:keyword%) AND h.account.id = :id ORDER BY h.id DESC")
    public Page<History> searchOrderByDate(@Param("id") Long id, @Param("keyword") String keyword, Pageable pageable);

    @Query(" SELECT h FROM History h WHERE (h.title LIKE %:keyword% OR h.link LIKE %:keyword%) AND h.account.id = :id ORDER BY h.title ")
    public Page<History> searchOrderByTitle(@Param("id") Long id, @Param("keyword") String keyword, Pageable pageable);

    @Query(" SELECT h FROM History h WHERE (h.title LIKE %:keyword% OR h.link LIKE %:keyword%) AND h.account.id = :id ORDER BY h.link ")
    public Page<History> searchOrderByLink(@Param("id") Long id, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT h FROM History h WHERE h.account.id = :accountId ORDER BY h.id DESC")
    public Page<History> findAllByAccountIdOrderByDate(Long accountId, Pageable pageable);

    @Query("SELECT h FROM History h WHERE h.account.id = :accountId ORDER BY h.title ")
    public Page<History> findAllByAccountIdOrderByTitle(Long accountId, Pageable pageable);

    @Query("SELECT h FROM History h WHERE h.account.id = :accountId ORDER BY h.link ")
    public Page<History> findAllByAccountIdOrderByLink(Long accountId, Pageable pageable);
}
