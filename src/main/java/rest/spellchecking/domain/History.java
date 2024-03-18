package rest.spellchecking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    @Column(columnDefinition = "nvarchar(255)")
    private String title;
    @Column(columnDefinition = "nvarchar(255)")
    private String link;
    @Column(columnDefinition = "nvarchar(max)")
    private String param;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
