package com.petcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;
    private Double weight;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Proprietaire owner;

    @ManyToOne
    @JoinColumn(name = "espece_id", nullable = false)
    private Espece espece;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<DossierMedical> medicalRecord;


}
