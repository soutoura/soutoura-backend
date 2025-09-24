package com.groupe.soutoura_backend.dto;

import java.util.Date;

public class NotificationDto {
    private int id;
    private String message;
    private Date dateEnvoie;
    private boolean estLus;
    private String type;
    private int utilisateurId;
    private String utilisateurNom;

    public NotificationDto() {}

    public NotificationDto(int id, String message, Date dateEnvoie, boolean estLus,
                           String type, int utilisateurId, String utilisateurNom) {
        this.id = id;
        this.message = message;
        this.dateEnvoie = dateEnvoie;
        this.estLus = estLus;
        this.type = type;
        this.utilisateurId = utilisateurId;
        this.utilisateurNom = utilisateurNom;
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getDateEnvoie() { return dateEnvoie; }
    public void setDateEnvoie(Date dateEnvoie) { this.dateEnvoie = dateEnvoie; }

    public boolean isEstLus() { return estLus; }
    public void setEstLus(boolean estLus) { this.estLus = estLus; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }

    public String getUtilisateurNom() { return utilisateurNom; }
    public void setUtilisateurNom(String utilisateurNom) { this.utilisateurNom = utilisateurNom; }
}