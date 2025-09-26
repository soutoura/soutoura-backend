package com.groupe.soutoura_backend.dto.responseDto;

import com.groupe.soutoura_backend.enume.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentDTO {
    private int idParent;
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String photoUrl;
    private Genre genre;
    private String adresse;
}
