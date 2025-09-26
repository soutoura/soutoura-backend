package com.groupe.soutoura_backend.dto.responseDto;

import com.groupe.soutoura_backend.enume.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationDTO {
    private int idAssociation;
    private int idUtilisateur;
    private String nomAssociation;
    private String telephone;
    private String email;
    private String photoUrl;
}
