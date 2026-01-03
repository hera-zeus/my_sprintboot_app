package com.petcare.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime consultationDate;
    private Double weightAtTheTime;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    private List<Vaccination> vaccinations;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    private List<Examen> examens;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    private List<Traitement> traitements;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    private DossierMedical medicalRecord;
}

