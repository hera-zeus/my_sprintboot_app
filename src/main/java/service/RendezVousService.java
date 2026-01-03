package service;

import model.*;
import repository.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
@Transactional
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final HoraireTravailRepository horaireTravailRepository;

    public RendezVous prendreRendezVous(RendezVous rdv) {
        // 1. Vérifier si le vétérinaire travaille ce jour-là
        DayOfWeek jour = rdv.getStartDate().getDayOfWeek();
        LocalTime debutHeure = rdv.getStartDate().toLocalTime();
        LocalTime finHeure = rdv.getEndDate().toLocalTime();

        HoraireTravail h = horaireTravailRepository
                .findByVeterinaryIdAndDay(rdv.getVeterinary().getId(), jour)
                .orElseThrow(() -> new RuntimeException("Le vétérinaire ne travaille pas ce jour-là"));

        // 2. Vérifier si le RDV est bien dans les heures d'ouverture
        // On vérifie que le début ET la fin sont dans les clous
        boolean estValide = (debutHeure.isAfter(h.getStartTime()) && finHeure.isBefore(h.getStartBreak()))
                || (debutHeure.isAfter(h.getEndBreak()) && finHeure.isBefore(h.getEndTime()));

        if (!estValide) {
            throw new RuntimeException("Le créneau choisi dépasse des horaires de service ou tombe pendant la pause.");
        }

        // 3. Vérification du CHEVAUCHEMENT (Conflit)
        boolean conflit = rendezVousRepository.hasOverlappingAppointment(
                rdv.getVeterinary().getId(),
                rdv.getStartDate(),
                rdv.getEndDate()
        );

        if (conflit) {
            throw new RuntimeException("Le vétérinaire est déjà occupé sur tout ou partie de ce créneau.");
        }

        return rendezVousRepository.save(rdv);
    }

    public void annulerRendezVous(Long rdvId) {
        RendezVous rdv = rendezVousRepository.findById(rdvId)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));
        rdv.setStatus(StatutRendezVous.annule); // On utilise ton Enum StatusRendezVous
        rendezVousRepository.save(rdv);
    }
}
