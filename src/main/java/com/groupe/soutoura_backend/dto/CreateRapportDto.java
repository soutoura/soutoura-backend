package com.groupe.soutoura_backend.dto;

import com.groupe.soutoura_backend.enume.Type;

public class CreateRapportDto {
    private Type type;
    private String periode;
    private String contenu;
    private int enfantId;

    public CreateRapportDto() {}

    // Getters/Setters
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public int getEnfantId() { return enfantId; }
    public void setEnfantId(int enfantId) { this.enfantId = enfantId; }
}