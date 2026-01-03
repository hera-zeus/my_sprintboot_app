package com.petcare.controller;

import com.petcare.model.Proprietaire;
import com.petcare.model.Utilisateur;
import com.petcare.model.Veterinaire;
import com.petcare.repository.UtilisateurRepository;
import com.petcare.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth") // Toutes les URLs commenceront par /api/auth
@RequiredArgsConstructor
public class UtilisateurController {
    @Autowired
    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    @PostMapping("/register/owner")
    public ResponseEntity<Proprietaire> registerOwner(@RequestBody Proprietaire owner) {
        return ResponseEntity.ok(utilisateurService.inscrireProprietaire(owner));
    }

    @PostMapping("/register/vet")
    public ResponseEntity<Veterinaire> registerVet(@RequestBody Veterinaire vet) {
        return ResponseEntity.ok(utilisateurService.inscrireVeterinaire(vet));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            Utilisateur user = utilisateurService.login(email, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
   // Important pour Flutter plus tard
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }
}
