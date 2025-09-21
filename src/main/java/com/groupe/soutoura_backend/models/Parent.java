package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String adresse;
    @Column
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @OneToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "parent")
    private List<Enfant> enfants = new ArrayList<>();
}
