package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Categorie;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Depense {
    private int id;
    private String intitule;
    private Double montant;
    private Date dateDepense;
    private String justificatif;
    private Categorie categorie;
}
