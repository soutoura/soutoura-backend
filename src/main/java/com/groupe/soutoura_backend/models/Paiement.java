package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Etat;
import com.groupe.soutoura_backend.enume.Mode;
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
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Double montant;
    @Column
    private Date datePaiement;
    @Column
    private Mode modePaiement;
    @Column
    @Enumerated(EnumType.STRING)
    private Etat statut;

    @ManyToOne
    @JoinColumn(name = "id_parrainage", nullable = false)
    private Parrainage parrainage;
}
