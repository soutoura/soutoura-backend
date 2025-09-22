package com.groupe.soutoura_backend.dto;

import com.groupe.soutoura_backend.enume.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestUtilisateur {
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private Role role;
    // Champs spécifiques à l'Association
    private String nomAssociation;
    private String adresse;
    private String papierAssociation;

    // Champs spécifiques au Parrain
    private String pays;
}
