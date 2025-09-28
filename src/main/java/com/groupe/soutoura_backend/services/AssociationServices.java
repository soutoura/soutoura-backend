package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.models.Enfant;
import com.groupe.soutoura_backend.repositories.AssociationRepo;
import com.groupe.soutoura_backend.repositories.EnfantRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociationServices {
    private final AssociationRepo associationRepo;
    private final EnfantRepo enfantRepo;

    public AssociationServices(AssociationRepo associationRepo, EnfantRepo enfantRepo) {
        this.associationRepo = associationRepo;
        this.enfantRepo = enfantRepo;
    }

    // Récupérer une association par son ID
    public Association getAssociationById(int id) {
        return associationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Association introuvable avec id " + id));
    }

    // Récupérer une association par son ID
    public Association getAssociationByUtilisateurId(int utilisateurId) {
        return associationRepo.findByUtilisateur_Id(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Association introuvable pour l'utilsiateur avec id" + utilisateurId));
    }

    //
    public List<Enfant> createEnfants(List<Enfant> enfants) {
        return enfantRepo.saveAll(enfants);
    }

}
