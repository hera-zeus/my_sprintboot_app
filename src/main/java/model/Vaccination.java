package model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Vaccination {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime injectionDate;
    private LocalDateTime scheduledReminderDate;
    private String lotNumber;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    private DossierMedical medicalRecord;

    @ManyToOne
    @JoinColumn(name = "type_vaccin_id", nullable = false)
    private TypeVaccin typeVaccin;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;
}
