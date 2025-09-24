package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

    List<Notification> findByUtilisateurId(int utilisateurId);

    List<Notification> findByUtilisateurIdAndEstLusFalse(int utilisateurId);

    Long countByUtilisateurIdAndEstLusFalse(int utilisateurId);

    @Modifying
    @Query("UPDATE Notification n SET n.estLus = true WHERE n.id = :id")
    void marquerCommeLue(@Param("id") int id);

    @Modifying
    @Query("UPDATE Notification n SET n.estLus = true WHERE n.utilisateur.id = :utilisateurId")
    void marquerToutesCommeLues(@Param("utilisateurId") int utilisateurId);
}