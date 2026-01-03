package com.petcare.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fileNumber;

    private LocalDateTime createDate;

    @Column(columnDefinition = "TEXT")
    private String medicalStory;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private List<Vaccination> vaccinations;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private List<Traitement> treatments;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }
}
