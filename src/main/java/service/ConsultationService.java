package service;

import model.*;
import repository.*;
import dto.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final VaccinationRepository vaccinationRepository;
    private final TraitementRepository traitementRepository;
    private final ExamenRepository examenRepository;

    public Consultation enregistrerPrescriptionComplete(PrescriptionDTO paquet) {
        // 1. SAUVEGARDER LA CONSULTATION
        Consultation consult = consultationRepository.save(paquet.getConsultation());

        // On récupère le dossier médical lié pour le transmettre aux autres actes
        DossierMedical dossier = consult.getMedicalRecord();

        // 2. TRAITER LES TRAITEMENTS (ORDONNANCES)
        if (paquet.getTraitements() != null && !paquet.getTraitements().isEmpty()) {
            for (Traitement t : paquet.getTraitements()) {
                t.setConsultation(consult); // On lie le traitement à cette visite
                traitementRepository.save(t);
            }
        }

        // 3. TRAITER LES VACCINS
        if (paquet.getVaccinations() != null && !paquet.getVaccinations().isEmpty()) {
            for (Vaccination v : paquet.getVaccinations()) {
                v.setMedicalRecord(dossier); // On lie au dossier de l'animal
                // Note : Ici on pourrait aussi appeler la logique de calcul
                // de date de rappel que nous avons vue précédemment.
                vaccinationRepository.save(v);
            }
        }

        // 4. TRAITER LES EXAMENS
        if (paquet.getExamens() != null && !paquet.getExamens().isEmpty()) {
            for (Examen e : paquet.getExamens()) {
                e.setMedicalRecord(dossier); // On lie au dossier de l'animal
                // On pourrait aussi lier l'examen à la consultation : e.setConsultation(consult);
                examenRepository.save(e);
            }
        }

        return consult;
    }

    public List<Consultation> historiqueAnimal(Long animalId) {
        return consultationRepository.findByMedicalRecordAnimalIdOrderByConsultationDateDesc(animalId);
    }
}
