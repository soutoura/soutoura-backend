package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.dto.EnfantParrainageDTO;
import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.models.Enfant;
import com.groupe.soutoura_backend.services.AssociationServices;
import com.groupe.soutoura_backend.services.EnfantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/associations")
public class AssociationControlers{
    @Autowired
    public AssociationServices associationServices;

    @Autowired
    public EnfantServices enfantServices;

    // GET /api/associations/utilisateur/{utilisateurId}
    @GetMapping(path = "/utilisateur/{utilisateurId}")
    public ResponseEntity<Association> getAssociationByUtilisateurId(@PathVariable int utilisateurId) {
        return ResponseEntity.ok(associationServices.getAssociationByUtilisateurId(utilisateurId));
    }

    // GET /api/associations/{idAssociation}
    @GetMapping("/{idAssociation}")
    public ResponseEntity<Association> getAssociationById(@PathVariable int idAssociation) {
        return ResponseEntity.ok(associationServices.getAssociationById(idAssociation));
    }

    // POST /api/associations/bulk
    @PostMapping("/bulk")
    public ResponseEntity<List<Enfant>> createEnfants(@RequestBody List<Enfant> enfants) {
        return ResponseEntity.ok(associationServices.createEnfants(enfants));
    }

    // Endpoint : total enfants
    @GetMapping(path = "/{associationId}/enfants")
    public ResponseEntity<Long> getTotalEnfants(@PathVariable int associationId) {
        return ResponseEntity.ok(enfantServices.countEnfantsByAssociation(associationId));
    }

    // Endpoint : enfants parrainés
    @GetMapping(path = "/{associationId}/enfants/parraines")
    public ResponseEntity<Long> getEnfantsParraines(@PathVariable int associationId) {
        return ResponseEntity.ok(enfantServices.countEnfantsParrainesByAssociation(associationId));
    }

    // Endpoint : enfants non parrainés
    @GetMapping(path = "/{associationId}/enfants/non-parraines")
    public ResponseEntity<Long> getEnfantsNonParraines(@PathVariable int associationId) {
        return ResponseEntity.ok(enfantServices.countEnfantsNonParrainesByAssociation(associationId));
    }

    // Endpoint pour obtenir le nombre de parrains distincts d'une association
    @GetMapping(path = "/{associationId}/parrains/count")
    public ResponseEntity<Long> getNombreParrainsDistincts(@PathVariable int associationId) {
        long nombeParrins = enfantServices.countParrainsByAssociation(associationId);
        return ResponseEntity.ok(nombeParrins);
    }

    // Endpoint : tous les enfants parrainés et non parrainés
    @GetMapping(path = "/{associationId}/enfants/status")
    public ResponseEntity<List<EnfantParrainageDTO>> getEnfantsAvecStatut(@PathVariable int associationId) {
        List<EnfantParrainageDTO> enfants = enfantServices.getEnfantsAvecStatut(associationId);
        return ResponseEntity.ok(enfants);
    }
}
