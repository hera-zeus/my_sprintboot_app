package service;


import model.*;
import repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // On optimise car ce ne sont que des lectures
public class ConfigurationService {

    private final EspeceRepository especeRepository;
    private final RaceRepository raceRepository;
    private final TypeVaccinRepository typeVaccinRepository;
    private final SpecialiteRepository specialiteRepository;
    private final TypeExamenRepository typeExamenRepository;

    // --- GESTION DES ESPÈCES ET RACES ---

    public List<Espece> listerToutesLesEspeces() {
        return especeRepository.findAll();
    }

    public List<Race> listerRacesParEspece(Long especeId) {
        // Très utile pour filtrer les races quand on choisit une espèce sur l'app
        return raceRepository.findByEspeceIdOrderByNameAsc(especeId);
    }

    // --- GESTION DES VACCINS ET SPÉCIALITÉS ---

    public List<TypeVaccin> listerTousLesTypesVaccin() {
        return typeVaccinRepository.findAll();
    }

    public List<Specialite> listerToutesLesSpecialites() {
        return specialiteRepository.findAll();
    }

    // --- GESTION DES EXAMENS ---

    public List<TypeExamen> listerTousLesTypesExamen() {
        return typeExamenRepository.findAll();
    }

    // --- MÉTHODES D'AJOUT (Pour l'admin ou le setup initial) ---

    @Transactional
    public Espece ajouterEspece(Espece espece) {
        return especeRepository.save(espece);
    }

    @Transactional
    public Race ajouterRace(Race race) {
        return raceRepository.save(race);
    }
}
