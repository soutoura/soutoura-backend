package com.groupe.soutoura_backend.mappers;


import com.groupe.soutoura_backend.dto.responseDto.ParrainageDTO;
import com.groupe.soutoura_backend.models.Parrainage;

public class ParrainageMapper {

    public static ParrainageDTO toDTO(Parrainage parrainage) {
        ParrainageDTO dto = new ParrainageDTO();
        dto.setIdParrainage(parrainage.getId());
        dto.setDateDebut(parrainage.getDateDebut());
        dto.setDateFin(parrainage.getDateFin());
        dto.setStatus(parrainage.getStatus());

        //info du parrain
        dto.setIdParrain(parrainage.getParrain().getId());
        dto.setPaysParrain(parrainage.getParrain().getPays());
        dto.setNomParrain(parrainage.getParrain().getUtilisateur().getNom());
        dto.setPrenomParrain(parrainage.getParrain().getUtilisateur().getPrenom());
        dto.setEmailParrain(parrainage.getParrain().getUtilisateur().getEmail());

        //info de l'enfant
        if (parrainage.getEnfant() != null) {
            dto.setIdEnfant(parrainage.getEnfant().getId());
            dto.setNomEnfant(parrainage.getEnfant().getNom());
            dto.setPrenomEnfant(parrainage.getEnfant().getPrenom());
            dto.setIdAssociation(parrainage.getEnfant().getAssociation().getId());
            dto.setNomAssociation(parrainage.getEnfant().getAssociation().getNomAssociation());
        }

        return dto;
    }
}