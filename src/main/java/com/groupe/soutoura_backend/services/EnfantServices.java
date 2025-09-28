package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.dto.EnfantParrainageDTO;
import com.groupe.soutoura_backend.enume.Status;
import com.groupe.soutoura_backend.models.Enfant;
import com.groupe.soutoura_backend.models.Parrainage;
import com.groupe.soutoura_backend.repositories.EnfantRepo;
import com.groupe.soutoura_backend.repositories.ParrainageRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnfantServices {
    private final EnfantRepo enfantRepo;
    private final ParrainageRepo parrainageRepo;

    public EnfantServices(EnfantRepo enfantRepo, ParrainageRepo parrainageRepo) {
        this.enfantRepo = enfantRepo;
        this.parrainageRepo = parrainageRepo;
    }

    public Enfant createEnfant(Enfant enfant) {
        return enfantRepo.save(enfant);
    }

    public Enfant updateEnfant(int id, Enfant enfantDetails) {
        Enfant enfant = enfantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Enfant non trouvé avec id " + id));

        enfant.setNom(enfantDetails.getNom() != null ?
                enfantDetails.getNom() : enfant.getNom());

        enfant.setPrenom(enfantDetails.getPrenom() != null ?
                enfantDetails.getPrenom() : enfant.getPrenom());

        enfant.setAge(enfantDetails.getAge());
        enfant.setNomTitulaire(enfantDetails.getNomTitulaire() != null
                ? enfantDetails.getNomTitulaire() : enfant.getNomTitulaire());

        enfant.setNumeroTelTitulaire(enfantDetails.getNumeroTelTitulaire()
                != null ? enfantDetails.getNumeroTelTitulaire() : enfant.getNumeroTelTitulaire());

        enfant.setNiveauScolaire(enfantDetails.getNiveauScolaire()
                != null ? enfantDetails.getNiveauScolaire() : enfant.getNiveauScolaire());

        enfant.setSexe(enfantDetails.getSexe() != null ? enfantDetails.getSexe() :
                enfant.getSexe());

        enfant.setPhoto(enfantDetails.getPhoto() != null ? enfantDetails.getPhoto() :
                enfant.getPhoto());

        enfant.setDateNaissance(enfantDetails.getDateNaissance() != null
                ? enfantDetails.getDateNaissance() : enfant.getDateNaissance());

        enfant.setParent(enfantDetails.getParent() != null ? enfantDetails.getParent()
                : enfant.getParent());

        enfant.setAssociation(enfantDetails.getAssociation() != null ? enfantDetails.getAssociation() :
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

    // Nombre total d'enfants dans une association
    public long countEnfantsByAssociation(int associationId) {
        return enfantRepo.countByAssociationId(associationId);
    }

    // Nombre d'enfants parrainés dans une association
    public long countEnfantsParrainesByAssociation(int associationId) {
        return parrainageRepo.findByEnfantAssociationId(associationId)
                .stream()
                .map(p -> p.getEnfant().getId())
                .distinct()
                .count();
    }

    // Nombre d'enfants non parrainés dans une association
    public long countEnfantsNonParrainesByAssociation(int associationId) {
        long total = countEnfantsByAssociation(associationId);
        long parraines = countEnfantsParrainesByAssociation(associationId);
        return total - parraines;
    }

    // Nombre de parrains distinct dans une association
    public long countParrainsByAssociation(int associationId) {
        return parrainageRepo.findByEnfantAssociationId(associationId)
                .stream()
                .map(p -> p.getParrain().getId())
                .distinct()
                .count();
    }

    // liste d’enfants enrichis avec un champ supplémentaire status
    @Transactional(readOnly = true)
    public List<EnfantParrainageDTO> getEnfantsAvecStatut(int associationId) {
        // Étape 1 : récupérer tous les enfants de l'association
        List<Enfant> enfants = enfantRepo.findByAssociationId(associationId);

        if (enfants.isEmpty()) {
            return Collections.emptyList();
        }

        // Étape 2 : récupérer tous les parrainages ACTIFS des enfants de l'association
        List<Integer> enfantIds = enfants.stream()
                .map(Enfant::getId)
                .toList();

        List<Parrainage> parrainagesActifs = parrainageRepo.findByEnfantIdInAndStatus(enfantIds, Status.ACTIF);

        // Étape 3 : construire un Set d'enfants parrainés
        Set<Integer> enfantsParraines = parrainagesActifs.stream()
                .map(p -> p.getEnfant().getId())
                .collect(Collectors.toSet());

        // Étape 4 : transformer en DTO
        return enfants
                .stream()
                .map(enfant -> new EnfantParrainageDTO(
                        enfant.getId(),
                        enfant.getNom(),
                        enfant.getPrenom(),
                        enfant.getPhoto(),
                        enfant.getAge(),
                        enfant.getNiveauScolaire(),
                        enfant.getDescription(),
                        enfantsParraines.contains(enfant.getId()) ? "PARRAINE" : "NON_PARRAINE"
                )).collect(Collectors.toList());
    }

}
