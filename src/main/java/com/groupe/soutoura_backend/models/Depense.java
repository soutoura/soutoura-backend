package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Categorie;
import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String intitule;
    @Column
    private Double montant;
    @Column
    private Date dateDepense;
    @Column
    private String justificatif;
    @Column
    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "id_enfant", nullable = false)
    private Enfant enfant;
}
