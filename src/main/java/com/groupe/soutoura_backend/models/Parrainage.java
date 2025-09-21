package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parrainage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Date dateDebut;
    @Column
    private Date dateFin;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_parrain", nullable = false)
    private Parrain parrain;

    @ManyToOne
    @JoinColumn(name = "id_enfant", nullable = false)
    private Enfant enfant;

    @OneToMany(mappedBy = "parrainage")
    private List<Paiement> paiements = new ArrayList<>();
}
