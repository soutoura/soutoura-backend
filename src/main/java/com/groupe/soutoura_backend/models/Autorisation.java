package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Authenum;
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
public class Autorisation {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private Authenum statut;
}
