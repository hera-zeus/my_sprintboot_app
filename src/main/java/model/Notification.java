package model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    private TypeNotification type;

    private LocalDateTime createDate;
    private LocalDateTime scheduledSendDate;

    private boolean read = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Utilisateur receiver;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }
}