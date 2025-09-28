package com.groupe.soutoura_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnfantParrainageDTO {
    private int id;
    private String nom;
    private String prenom;
    private String photo;
    private int age;
    private String niveauScolaire;
    private String bio;
    private String status;
}
