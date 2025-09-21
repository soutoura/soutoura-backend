package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enfant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String nomTitulaire;
    @Column(nullable = false)
    private String numeroTelTitulaire;
    @Column(nullable = false)
    private String niveauScolaire;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre sexe;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false)
    private LocalDate dateNaissance;

    //liste dépenses
    @OneToMany(mappedBy = "enfant")
    private List<Depense> depenses = new ArrayList<>();

    //liste rapport
    @OneToMany(mappedBy = "enfant")
    private List<Rapport> rapports = new ArrayList<>();

    //liste rapport pedagogique
    @OneToMany(mappedBy = "enfant")
    private List<RapportPedagogique> rapportPedagogiques= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_parent", nullable = false)
    private Parent parent;


}
