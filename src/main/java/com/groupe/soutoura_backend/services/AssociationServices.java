package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.repositories.AssociationRepo;
import org.springframework.stereotype.Service;

@Service
public class AssociationServices {
    private final AssociationRepo associationRepo;

    public AssociationServices(AssociationRepo associationRepo) {
        this.associationRepo = associationRepo;
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

}
