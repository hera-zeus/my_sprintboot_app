package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Traitement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dosage;
    private String duration;

    @Column(columnDefinition = "TEXT")
    private String specialInstruction;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private Medicament medication;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    private DossierMedical medicalRecord;
}
