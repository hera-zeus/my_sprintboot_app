package com.petcare.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.time.DayOfWeek;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class HoraireTravail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    private LocalTime startTime;
    private LocalTime endTime;

    private LocalTime startBreak;
    private LocalTime endBreak;

    @ManyToOne
    @JoinColumn(name = "veterinary_id")
    private Veterinaire veterinary;
}
