package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Type;
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
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Type type;
    @Column
    private String periode;
    @Column
    private String contenu;
    @Column
    private Date dateCreation;
    @ManyToOne
    @JoinColumn(name = "id_enfant", nullable = false)
    private Enfant enfant ;
}
