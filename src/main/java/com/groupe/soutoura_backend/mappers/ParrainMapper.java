package com.groupe.soutoura_backend.mappers;

import com.groupe.soutoura_backend.dto.responseDto.ParrainDTO;
import com.groupe.soutoura_backend.models.Parrain;
import com.groupe.soutoura_backend.models.Utilisateur;

public class ParrainMapper {

    public static ParrainDTO toDTO(Parrain parrain) {
        if (parrain == null || parrain.getUtilisateur() == null) {
            return null;
        }

        Utilisateur u = parrain.getUtilisateur();

        return new ParrainDTO(
                u.getId(),
                parrain.getId(),
                u.getNom(),
                u.getPrenom(),
                u.getEmail(),
                u.getTelephone(),
                parrain.getPays(),
                u.getPhotoUrl()
        );
    }
}
