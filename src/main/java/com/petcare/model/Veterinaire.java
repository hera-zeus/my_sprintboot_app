package com.petcare.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Veterinaire extends Utilisateur{

    private String clinicName;
    private String clinicAddress;

    @Column(unique = true)
    private String licenseNumber;

    @ManyToOne
    @JoinColumn(name = "specialite_id", nullable = false)
    private Specialite speciality;

    @OneToMany(mappedBy = "veterinary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HoraireTravail> schedules;
}
