package repository;

import model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findByMedicalRecordAnimalId(Long animalId);

    // Pour ton système de rappel : trouve les vaccins dont le rappel est dû entre deux dates
    // (Utile pour envoyer une notification 7 jours avant la date de rappel)
    List<Vaccination> findByScheduledReminderDateBetween(LocalDateTime start, LocalDateTime end);
}
