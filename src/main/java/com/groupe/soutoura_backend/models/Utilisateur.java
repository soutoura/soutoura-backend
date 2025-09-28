package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Role;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private String photoUrl;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String motDePasse;
    @Column
    private boolean estActif;
    @Column
    private Date dateCreation;
    @Column
    private String telephone;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utilisateur")
    private List<Notification> Notifications = new ArrayList<>();

    //liste des authorisations
    @OneToMany(mappedBy = "utilisateur")
    private List<Autorisation> authorisations = new ArrayList<>();
}
