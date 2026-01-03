package com.petcare.controller;

import com.petcare.model.Animal;
import com.petcare.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnimalController {

    private final AnimalService animalService;

    // Ajouter un nouvel animal
    @PostMapping("/register")
    public ResponseEntity<Animal> registerAnimal(@RequestBody Animal animal) {
        // Le service créera aussi automatiquement le DossierMedical
        return ResponseEntity.ok(animalService.enregistrerAnimal(animal));
    }

    // Lister les animaux d'un propriétaire spécifique
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Animal>> getAnimalsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(animalService.recupererAnimauxParProprio(ownerId));
    }

    // Récupérer les détails d'un animal par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id) // Assure-toi d'avoir cette méthode dans ton service
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
