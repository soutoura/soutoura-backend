package com.groupe.soutoura_backend.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParrainDTO {
    private int IdParrain;
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String Telephone;
    private String pays;
    private String photoUrl;
}
