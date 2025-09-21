package com.groupe.soutoura_backend.models;

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
public class RapportPedagogique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String periode;
    @Column
    private int absence;
    @Column
    private Double moyenneFinale;
    @Column
    private String bulletinPdf;
    @Column
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_rapportpedagogique", nullable = false)
    private RapportPedagogique rapportPedagogique;

    @ManyToOne
    @JoinColumn(name = "id_enfant", nullable = false)
    private Enfant enfant;
}
