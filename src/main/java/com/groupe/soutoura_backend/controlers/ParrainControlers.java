package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Parrain;
import com.groupe.soutoura_backend.services.ParrainServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parrains")
public class ParrainControlers {

    private final ParrainServices parrainServices;
    public ParrainControlers(ParrainServices parrainServices){
        this.parrainServices = parrainServices;
    }

    // Récupérer un parrain par son id
    @GetMapping("/{id}")
    public ResponseEntity<Parrain> getParrain(@PathVariable int id) {
        return ResponseEntity.ok(parrainServices.getParrainById(id));
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Parrain> getParrainByUtilisateur(@PathVariable int utilisateurId) {
        return ResponseEntity.ok(parrainServices.getParrainByUtilisateurId(utilisateurId));
    }

    @GetMapping
    public ResponseEntity<List<Parrain>> getAllParrains() {
        return ResponseEntity.ok(parrainServices.getAllParrains());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parrain> updateParrain(
            @PathVariable int id,
            @RequestParam String pays) {
        return ResponseEntity.ok(parrainServices.updateParrain(id, pays));
    }

    // Supprimer un parrain
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParrain(@PathVariable int id) {
        parrainServices.deleteParrain(id);
        return ResponseEntity.noContent().build();
    }
}
