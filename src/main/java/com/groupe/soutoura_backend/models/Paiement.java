package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Etat;
import com.groupe.soutoura_backend.enume.Mode;
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
public class Paiement {
    private int id;
    private Double montant;
    private Date datePaiement;
    private Mode modePaiement;
    private Etat statut;
}
