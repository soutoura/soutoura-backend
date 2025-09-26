package com.groupe.soutoura_backend.dto.responseDto;


import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AssociationMapper {

    // Convertir entité -> DTO
    public static AssociationDTO toDTO(Association association) {
        if (association == null) return null;

        Utilisateur utilisateur = association.getUtilisateur();

        return new AssociationDTO(
                association.getId(),
                utilisateur.getId(),
                association.getNomAssociation(),
                utilisateur.getTelephone(),
                utilisateur.getEmail() ,
                utilisateur.getPhotoUrl()
        );
    }
}
