package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Role;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
}
