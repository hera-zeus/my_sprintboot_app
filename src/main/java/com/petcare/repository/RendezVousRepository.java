package com.petcare.repository;

import com.petcare.model.RendezVous;
import com.petcare.model.StatutRendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    // Pour le vétérinaire : voir ses RDV du jour
    List<RendezVous> findByVeterinaryIdAndDateRendezVousBetween(
            Long veterinaryId, LocalDateTime startDate, LocalDateTime endDate);

    // Pour le propriétaire : voir ses rendez-vous à venir
    List<RendezVous> findByAnimalOwnerIdAndStatus(Long ownerId, StatutRendezVous status);

    // Vérifie s'il existe un RDV qui chevauche la période donnée pour un vétérinaire
    @Query("SELECT COUNT(r) > 0 FROM RendezVous r WHERE r.veterinary.id = :vetoId " +
            "AND r.status <> 'ANNULE' " +
            "AND r.startDate < :newEnd AND r.endDate > :newStart")
    boolean hasOverlappingAppointment(
            @Param("vetoId") Long vetoId,
            @Param("newStart") LocalDateTime newStart,
            @Param("newEnd") LocalDateTime newEnd
    );
}