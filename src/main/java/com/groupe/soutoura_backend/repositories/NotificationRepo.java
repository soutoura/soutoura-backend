package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Notification;
import com.groupe.soutoura_backend.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {
    List<Notification> findByUtilisateurOrderByDateEnvoieDesc(Utilisateur utilisateur);
}
