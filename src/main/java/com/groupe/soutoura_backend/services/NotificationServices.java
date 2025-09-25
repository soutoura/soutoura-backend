package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Notification;
import com.groupe.soutoura_backend.models.Utilisateur;
import com.groupe.soutoura_backend.repositories.NotificationRepo;
import com.groupe.soutoura_backend.repositories.UtilisateurRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationServices {
    private final NotificationRepo notificationRepository;
    private final UtilisateurRepo utilisateurRepository;

    public NotificationServices(NotificationRepo notificationRepository,
                                UtilisateurRepo utilisateurRepository
                                )
    {
        this.notificationRepository = notificationRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    //creer une notification à un utilisateur
    public Notification envoyerNotification(int userId, String message, String type) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable : " + userId));

        Notification notification = new Notification();
        notification.setUtilisateur(utilisateur);
        notification.setMessage(message);
        notification.setDateEnvoie(new Date());
        notification.setEstLus(false);
        notification.setType(type);

        return notificationRepository.save(notification);
    }


    //
    public List<Notification> getNotifications(Utilisateur utilisateur) {
        return notificationRepository.findByUtilisateurOrderByDateEnvoieDesc(utilisateur);
    }

    public List<Notification> getNotifications(int userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable : " + userId));
        return notificationRepository.findByUtilisateurOrderByDateEnvoieDesc(utilisateur);
    }

    // Marquer une notification comme lue
    public void marquerCommeLue(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification introuvable avec id: " + notificationId));
        notification.setEstLus(true);
        notificationRepository.save(notification);
    }
}
