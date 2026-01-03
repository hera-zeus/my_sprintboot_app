package model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    private Long duration;

    @Enumerated(EnumType.STRING)
    private StatutRendezVous status;

    @Enumerated(EnumType.STRING)
    private TypeRendezVous reason;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "veterinary_id", nullable = false)
    private Veterinaire veterinary;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Proprietaire owner;

    @PrePersist
    @PreUpdate
    public void calculateDuration() {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début.");
        }
        if (startDate != null && endDate != null) {
            this.duration = Duration.between(startDate, endDate).toMinutes();
        }
    }
}
