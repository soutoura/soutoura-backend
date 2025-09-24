package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.services.AssociationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/associations")
public class AssociationControlers{
    @Autowired
    public AssociationServices associationServices;

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
}
