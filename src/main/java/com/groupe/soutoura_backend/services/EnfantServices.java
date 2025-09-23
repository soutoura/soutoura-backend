package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Enfant;
import com.groupe.soutoura_backend.repositories.EnfantRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnfantServices {
    private final EnfantRepo enfantRepo;

    public EnfantServices(EnfantRepo enfantRepo) {
        this.enfantRepo = enfantRepo;
    }

    public Enfant createEnfant(Enfant enfant) {
        return enfantRepo.save(enfant);
    }

    public Enfant updateEnfant(int id, Enfant enfantDetails) {
        Enfant enfant = enfantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Enfant non trouvé avec id " + id));

        enfant.setNom(enfantDetails.getNom()!= null ?
                enfantDetails.getNom() : enfant.getNom());

        enfant.setPrenom(enfantDetails.getPrenom()!= null ?
                enfantDetails.getPrenom() : enfant.getPrenom());

        enfant.setAge(enfantDetails.getAge());
        enfant.setNomTitulaire(enfantDetails.getNomTitulaire() != null
        ? enfantDetails.getNomTitulaire() : enfant.getNomTitulaire());

        enfant.setNumeroTelTitulaire(enfantDetails.getNumeroTelTitulaire()
        != null ? enfantDetails.getNumeroTelTitulaire() : enfant.getNumeroTelTitulaire());

        enfant.setNiveauScolaire(enfantDetails.getNiveauScolaire()
        != null ? enfantDetails.getNiveauScolaire(): enfant.getNiveauScolaire());

        enfant.setSexe(enfantDetails.getSexe() != null ? enfantDetails.getSexe():
                enfant.getSexe());

        enfant.setPhoto(enfantDetails.getPhoto() != null ? enfantDetails.getPhoto():
                enfant.getPhoto());

        enfant.setDateNaissance(enfantDetails.getDateNaissance() != null
        ? enfantDetails.getDateNaissance(): enfant.getDateNaissance());

        enfant.setParent(enfantDetails.getParent() != null ? enfantDetails.getParent()
                : enfant.getParent());

        enfant.setAssociation(enfantDetails.getAssociation() != null ? enfantDetails.getAssociation():
                enfant.getAssociation());

        return enfantRepo.save(enfant);
    }
    public void deleteEnfant(int id) {
        enfantRepo.deleteById(id);
    }

    public Optional<Enfant> getEnfantById(int id) {
        return enfantRepo.findById(id);
    }

    public List<Enfant> getAllEnfants() {
        return enfantRepo.findAll();
    }

    public List<Enfant> getEnfantsByParentId(int parentId) {
        return enfantRepo.findByParentId(parentId);
    }

    public List<Enfant> getEnfantsByAssociationId(int associationId) {
        return enfantRepo.findByAssociationId(associationId);
    }
}
