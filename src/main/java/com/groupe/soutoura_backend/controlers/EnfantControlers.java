package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Enfant;
import com.groupe.soutoura_backend.services.EnfantServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enfants")
public class EnfantControlers
{
    private final EnfantServices enfantService;

    public EnfantControlers(EnfantServices enfantService) {
        this.enfantService = enfantService;
    }
    @PostMapping
    public ResponseEntity<Enfant> createEnfant(@RequestBody Enfant enfant) {
        return ResponseEntity.ok(enfantService.createEnfant(enfant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enfant> updateEnfant(@PathVariable int id, @RequestBody Enfant enfant) {
        return ResponseEntity.ok(enfantService.updateEnfant(id, enfant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnfant(@PathVariable int id) {
        enfantService.deleteEnfant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enfant> getEnfantById(@PathVariable int id) {
        return enfantService.getEnfantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<Enfant>> getAllEnfants() {
        return ResponseEntity.ok(enfantService.getAllEnfants());
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<Enfant>> getEnfantsByParent(@PathVariable int parentId) {
        return ResponseEntity.ok(enfantService.getEnfantsByParentId(parentId));
    }

    @GetMapping("/association/{associationId}")
    public ResponseEntity<List<Enfant>> getEnfantsByAssociation(@PathVariable int associationId) {
        return ResponseEntity.ok(enfantService.getEnfantsByAssociationId(associationId));
    }
}
