package com.groupe.soutoura_backend.dto.responseDto;
import com.groupe.soutoura_backend.enume.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParrainageDTO {
    private int idParrainage;
    private Date dateDebut;
    private Date dateFin;
    private Status status;

    // Champs du parrain (extraits de l'entité Parrain + Utilisateur)
    private int idParrain;
    private String paysParrain;
    private String nomParrain;
    private String prenomParrain;
    private String emailParrain;

    // (optionnel) Infos enfant si besoin
    private int idEnfant;
    private String nomEnfant;
    private String prenomEnfant;

    //information de l'association
    private int idAssociation;
    private String nomAssociation;
}
