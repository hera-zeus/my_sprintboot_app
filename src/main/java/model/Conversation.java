package model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Proprietaire owner;

    @ManyToOne
    @JoinColumn(name = "veterinary_id")
    private Veterinaire veterinary;

    private LocalDateTime createDate;
    private LocalDateTime lastMessageDate;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @OrderBy("sentAt ASC")
    private List<Message> messages;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.lastMessageDate = LocalDateTime.now();
    }
}
