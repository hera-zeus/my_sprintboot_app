package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String molecule;

    @Enumerated(EnumType.STRING)
    private FormeMedicament shape;

    @Column(columnDefinition = "TEXT")
    private String contraindication;
}
