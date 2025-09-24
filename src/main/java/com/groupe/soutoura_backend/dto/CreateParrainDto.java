package com.groupe.soutoura_backend.dto;

public class CreateParrainDto {
    private String pays;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;

    public CreateParrainDto() {}

    // Getters/Setters
    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}