package dto;

import model.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class PrescriptionDTO {
    // Les infos de la consultation elle-même
    private Consultation consultation;

    // Ce que le véto a décidé de prescrire
    private List<Traitement> traitements;
    private List<Vaccination> vaccinations;
    private List<Examen> examens;
}
