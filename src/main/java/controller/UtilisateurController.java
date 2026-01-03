package controller;

import model.*;
import service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth") // Toutes les URLs commenceront par /api/auth
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

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
}
