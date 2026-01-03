package com.petcare.controller;

import com.petcare.dto.PrescriptionDTO;
import com.petcare.model.Consultation;
import com.petcare.model.Vaccination;
import com.petcare.repository.ExamenRepository;
import com.petcare.repository.TraitementRepository;
import com.petcare.service.ConsultationService;
import com.petcare.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConsultationController {

    private final ConsultationService consultationService;
    private final VaccinationService vaccinationService;
    private final TraitementRepository traitementRepository;
    private final ExamenRepository examenRepository;

    // --- CONSULTATIONS ---

    @PostMapping("/prescrire")
    public ResponseEntity<Consultation> prescrireTout(@RequestBody PrescriptionDTO paquet) {
        try {
            Consultation resultat = consultationService.enregistrerPrescriptionComplete(paquet);
            return ResponseEntity.ok(resultat);
        } catch (Exception e) {
            // En cas d'erreur (ex: ID d'animal inexistant), on renvoie une erreur 400
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/history/{animalId}")
    public ResponseEntity<List<Consultation>> getHistory(@PathVariable Long animalId) {
        return ResponseEntity.ok(consultationService.historiqueAnimal(animalId));
    }

    // --- VACCINATIONS ---

    @PostMapping("/vaccinations")
    public ResponseEntity<Vaccination> addVaccination(@RequestBody Vaccination vaccination) {
        // Le service calculera automatiquement la date de rappel (reminderMonth)
        return ResponseEntity.ok(vaccinationService.enregistrerVaccin(vaccination));
    }
}
