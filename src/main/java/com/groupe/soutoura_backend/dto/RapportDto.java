package com.groupe.soutoura_backend.dto;

import com.groupe.soutoura_backend.enume.Type;
import java.util.Date;

public class RapportDto {
    private int id;
    private Type type;
    private String periode;
    private String contenu;
    private Date dateCreation;
    private int enfantId;
    private String enfantNom;

    public RapportDto() {}

    public RapportDto(int id, Type type, String periode, String contenu,
                      Date dateCreation, int enfantId, String enfantNom) {
        this.id = id;
        this.type = type;
        this.periode = periode;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.enfantId = enfantId;
        this.enfantNom = enfantNom;
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }

    public int getEnfantId() { return enfantId; }
    public void setEnfantId(int enfantId) { this.enfantId = enfantId; }

    public String getEnfantNom() { return enfantNom; }
    public void setEnfantNom(String enfantNom) { this.enfantNom = enfantNom; }
}