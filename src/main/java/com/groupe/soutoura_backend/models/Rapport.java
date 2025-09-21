package com.groupe.soutoura_backend.models;

import com.groupe.soutoura_backend.enume.Type;
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
public class Rapport {
    private int id;
    private Type type;
    private String periode;
    private String contenu;
    private Date dateCreation;
}
