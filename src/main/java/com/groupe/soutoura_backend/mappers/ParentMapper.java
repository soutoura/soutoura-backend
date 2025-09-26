package com.groupe.soutoura_backend.mappers;



import com.groupe.soutoura_backend.dto.responseDto.ParentDTO;
import com.groupe.soutoura_backend.models.Parent;
import com.groupe.soutoura_backend.models.Utilisateur;

public class ParentMapper {

    // Convertir entité -> DTO
    public static ParentDTO toDTO(Parent parent) {
        if (parent == null) return null;

        Utilisateur utilisateur = parent.getUtilisateur();

        return new ParentDTO(
                parent.getId(),
                utilisateur.getId(),
                utilisateur.getNom() ,
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone(),
                utilisateur.getPhotoUrl(),
                parent.getGenre(),
                parent.getAdresse()
        );
    }
}
