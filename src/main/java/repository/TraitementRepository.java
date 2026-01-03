package repository;

import model.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TraitementRepository extends JpaRepository<Traitement, Long> {

    List<Traitement> findByMedicalRecordAnimalId(Long animalId);

    List<Traitement> findByConsultationId(Long consultationId);

    List<Traitement> findByMedicalRecordAnimalIdAndMedicationId(Long animalId, Long medicationId);
}
