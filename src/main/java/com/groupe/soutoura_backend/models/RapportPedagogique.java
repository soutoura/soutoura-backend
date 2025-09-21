package com.groupe.soutoura_backend.models;

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
public class RapportPedagogique {
    private int id;
    private String periode;
    private int absence;
    private Double moyenneFinale;
    private String bulletinPdf;
    private Date dateCreation;
}
