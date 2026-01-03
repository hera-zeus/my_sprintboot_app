package com.petcare.repository;

import com.petcare.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    // Voir toutes les consultations passées d'un animal (triées par date)
    List<Consultation> findByMedicalRecordAnimalIdOrderByConsultationDateDesc(Long animalId);
}
