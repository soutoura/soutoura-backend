package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Authenum;
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
public class Autorisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date dateDebut;
    @Column
    private Date dateFin;
    @Column
    @Enumerated(EnumType.STRING)
    private Authenum statut;

    @OneToOne
    @JoinColumn(name = "id_rapportpedagogique")
    private RapportPedagogique rapportPedagogique;
}
