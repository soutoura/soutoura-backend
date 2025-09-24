package com.groupe.soutoura_backend.dto;

public class CreateNotificationDto {
    private String message;
    private String type;
    private int utilisateurId;

    public CreateNotificationDto() {}

    // Getters/Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }
}