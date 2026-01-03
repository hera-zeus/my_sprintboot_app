package service;

import model.*;
import repository.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final DossierMedicalRepository dossierMedicalRepository;

    public Animal enregistrerAnimal(Animal animal) {
        // 1. Sauvegarder l'animal
        Animal savedAnimal = animalRepository.save(animal);

        // 2. Créer automatiquement son dossier médical lié
        DossierMedical dossier = DossierMedical.builder()
                .animal(savedAnimal)
                .build();
        dossierMedicalRepository.save(dossier);

        return savedAnimal;
    }

    @Transactional(readOnly = true)
    public List<Animal> recupererAnimauxParProprio(Long ownerId) {
        return animalRepository.findByOwnerId(ownerId);
    }

    @Transactional(readOnly = true)
    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

}
