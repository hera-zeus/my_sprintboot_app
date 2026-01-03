package com.petcare.controller;

import com.petcare.model.*;
import com.petcare.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    // --- RÉFÉRENTIELS ANIMAUX ---

    @GetMapping("/especes")
    public ResponseEntity<List<Espece>> getAllEspeces() {
        return ResponseEntity.ok(configurationService.listerToutesLesEspeces());
    }

    @GetMapping("/especes/{id}/races")
    public ResponseEntity<List<Race>> getRacesByEspece(@PathVariable Long id) {
        return ResponseEntity.ok(configurationService.listerRacesParEspece(id));
    }

    // --- RÉFÉRENTIELS MÉDICAUX ---

    @GetMapping("/vaccins/types")
    public ResponseEntity<List<TypeVaccin>> getAllTypeVaccins() {
        return ResponseEntity.ok(configurationService.listerTousLesTypesVaccin());
    }

    @GetMapping("/specialites")
    public ResponseEntity<List<Specialite>> getAllSpecialites() {
        return ResponseEntity.ok(configurationService.listerToutesLesSpecialites());
    }

    @GetMapping("/examens/types")
    public ResponseEntity<List<TypeExamen>> getAllTypeExamens() {
        return ResponseEntity.ok(configurationService.listerTousLesTypesExamen());
    }

    // --- AJOUTS (POUR INITIALISER TES DONNÉES) ---

    @PostMapping("/especes")
    public ResponseEntity<Espece> createEspece(@RequestBody Espece espece) {
        return ResponseEntity.ok(configurationService.ajouterEspece(espece));
    }

    @PostMapping("/races")
    public ResponseEntity<Race> createRace(@RequestBody Race race) {
        return ResponseEntity.ok(configurationService.ajouterRace(race));
    }
}
