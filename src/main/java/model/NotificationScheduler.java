//package model;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class NotificationScheduler {
//
//    @Autowired
//    private VaccinationRepository vaccRepo;
//
//    @Autowired
//    private NotificationRepository notifRepo;
//
//    // S'exécute tous les jours à 8h du matin
//    @Scheduled(cron = "0 0 8 * * *")
//    public void verifierRappelsVaccins() {
//        LocalDate dansTroisJours = LocalDate.now().plusDays(3);
//
//        // On cherche les vaccinations dont le rappel est dans 3 jours
//        List<Vaccination> vaccins = vaccRepo.findAllByDateRappelPrevue(dansTroisJours);
//
//        for (Vaccination v : vaccins) {
//            Notification n = Notification.builder()
//                    .titre("Rappel Vaccin : " + v.getTypeVaccin().getNom())
//                    .message("Le rappel pour " + v.getDossierMedical().getAnimal().getNom() + " est prévu le " + v.getDateRappelPrevue())
//                    .destinataire(v.getDossierMedical().getAnimal().getProprietaire())
//                    .type(TypeNotification.VACCIN)
//                    .dateEnvoiPrevue(LocalDateTime.now())
//                    .build();
//
//            notifRepo.save(n);
//            // Ici, on appellerait aussi le service Firebase (FCM) pour le push mobile
//        }
//    }
//}
