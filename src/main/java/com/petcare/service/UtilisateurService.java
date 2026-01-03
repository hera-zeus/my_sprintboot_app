package com.petcare.service;

import com.petcare.model.Proprietaire;
import com.petcare.model.Utilisateur;
import com.petcare.model.Veterinaire;
import com.petcare.repository.ProprietaireRepository;
import com.petcare.repository.UtilisateurRepository;
import com.petcare.repository.VeterinaireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ProprietaireRepository proprietaireRepository;
    private final VeterinaireRepository veterinaireRepository;

    // Pour plus tard : BCryptPasswordEncoder pour hacher les mots de passe
    // private final PasswordEncoder passwordEncoder;

    /**
     * Inscription d'un nouveau propriétaire
     */
    @Transactional
    public Proprietaire inscrireProprietaire(Proprietaire proprio) {
        if (utilisateurRepository.existsByEmail(proprio.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        // Ici, on hacherait le mot de passe :
        // proprio.setPassword(passwordEncoder.encode(proprio.getPassword()));
        return proprietaireRepository.save(proprio);
    }

    /**
     * Inscription d'un nouveau vétérinaire
     */
    @Transactional
    public Veterinaire inscrireVeterinaire(Veterinaire veto) {
        if (utilisateurRepository.existsByEmail(veto.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        return veterinaireRepository.save(veto);
    }

    /**
     * Logique de connexion simplifiée (Avant mise en place de Spring Security)
     */
    public Utilisateur login(String email, String password) {
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérification simplifiée (à remplacer par passwordEncoder.matches)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        return user;
    }

    public List<Utilisateur> listerToutLeMonde() {
        return utilisateurRepository.findAll();
    }
}
