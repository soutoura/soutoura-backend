package com.groupe.soutoura_backend.dto;

import com.groupe.soutoura_backend.models.Utilisateur;

public class ParrainDto {
    private int id;
    private String pays;
    private Utilisateur utilisateur;

    public ParrainDto() {}

    public ParrainDto(int id, String pays, Utilisateur utilisateur) {
        this.id = id;
        this.pays = pays;
        this.utilisateur = utilisateur;
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
}