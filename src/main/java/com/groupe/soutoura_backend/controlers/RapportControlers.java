package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Rapport;
import com.groupe.soutoura_backend.services.RapportServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapportControlers {
    private final RapportServices rapportService;

    public RapportControlers(RapportServices rapportService) {
        this.rapportService = rapportService;
    }

    @PostMapping
    public ResponseEntity<Rapport> createRapport(@RequestBody Rapport rapport) {
        return ResponseEntity.ok(rapportService.createRapport(rapport));
    }

    @GetMapping
    public ResponseEntity<List<Rapport>> getAllRapports() {
        return ResponseEntity.ok(rapportService.getAllRapports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rapport> getRapportById(@PathVariable int id) {
        return rapportService.getRapportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rapport> updateRapport(@PathVariable int id, @RequestBody Rapport rapportDetails) {
        return ResponseEntity.ok(rapportService.updateRapport(id, rapportDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRapport(@PathVariable int id) {
        rapportService.deleteRapport(id);
        return ResponseEntity.noContent().build();
    }
}
