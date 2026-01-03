package com.petcare.service;

import com.petcare.model.Notification;
import com.petcare.model.Utilisateur;
import com.petcare.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void creerNotification(Utilisateur destinataire, String titre, String contenu) {
        Notification note = Notification.builder()
                .receiver(destinataire)
                .title(titre)
                .message(contenu)
                .createDate(LocalDateTime.now())
                .read(false)
                .build();
        notificationRepository.save(note);
    }

    public void marquerCommeLu(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(n -> {
            n.setRead(true);
            notificationRepository.save(n);
        });
    }
}
