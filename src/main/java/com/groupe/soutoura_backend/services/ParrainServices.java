package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Notification;
import com.groupe.soutoura_backend.models.Parrain;
import com.groupe.soutoura_backend.repositories.ParrainRepo;
import com.groupe.soutoura_backend.repositories.ParrainageRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParrainServices {
    private final ParrainRepo parrainRepo;

    public ParrainServices(ParrainRepo parrainRepo) {
        this.parrainRepo = parrainRepo;
    }

    public Parrain getParrainById(int id) {
        return parrainRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parrain introuvable"));
    }

    /**
     * Récupérer un parrain par id utilisateur
     */
    public Parrain getParrainByUtilisateurId(int utilisateurId) {
        return parrainRepo.findByUtilisateurId(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Parrain introuvable pour cet utilisateur"));
    }

    public List<Notification> getNotificationsParrain(int parrainId) {
        Parrain parrain = parrainRepo.findById(parrainId)
                .orElseThrow(() -> new RuntimeException("Parrain introuvable"));
        return parrain.getUtilisateur().getNotifications();
    }

    /**
     * Récupérer tous les parrains
     */
    public List<Parrain> getAllParrains() {
        return parrainRepo.findAll();
    }
    /**
     * Mettre à jour le pays d’un parrain
     */
    public Parrain updateParrain(int id, String pays) {
        Parrain parrain = getParrainById(id);
        parrain.setPays(pays);
        return parrainRepo.save(parrain);
    }

    /**
     * Supprimer un parrain
     */
    public void deleteParrain(int id) {
        Parrain parrain = getParrainById(id);
        parrainRepo.delete(parrain);
    }


}
