package controller;

import model.*;
import service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody RendezVous rdv) {
        try {
            RendezVous savedRdv = rendezVousService.prendreRendezVous(rdv);
            return ResponseEntity.ok(savedRdv);
        } catch (RuntimeException e) {
            // Si le service lance une exception (conflit, hors horaires), on renvoie l'erreur
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
