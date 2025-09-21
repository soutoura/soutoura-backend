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
public class Notification {
    private int id;
    private String message;
    private Date dateEnvoie;
    private boolean estLus;
    private String type;
}
