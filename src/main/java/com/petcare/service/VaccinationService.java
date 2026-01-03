package com.petcare.service;

import com.petcare.model.TypeVaccin;
import com.petcare.model.Vaccination;
import com.petcare.repository.TypeVaccinRepository;
import com.petcare.repository.VaccinationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final TypeVaccinRepository typeVaccinRepository;

    public Vaccination enregistrerVaccin(Vaccination vacc) {
        // 1. Chercher les infos du type de vaccin (pour avoir le reminderMonth)
        TypeVaccin type = typeVaccinRepository.findById(vacc.getTypeVaccin().getId())
                .orElseThrow(() -> new RuntimeException("Type de vaccin inconnu"));

        // 2. Calculer la date de rappel automatique
        // Si le vaccin a un rappel (ex: 12 mois)
        if (type.getReminderMonth() != null) {
            vacc.setScheduledReminderDate(vacc.getInjectionDate().plusMonths(type.getReminderMonth()));
        }

        return vaccinationRepository.save(vacc);
    }
}
