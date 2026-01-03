package com.petcare.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Examen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String result;

    private boolean execute = false;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "type_exam_id", nullable = false)
    private TypeExamen typeExam;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    private DossierMedical medicalRecord;

}

