package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Paiement;
import com.groupe.soutoura_backend.services.PaiementServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementControlers {
    private final PaiementServices paiementServices;

    public PaiementControlers(PaiementServices paiementServices) {
        this.paiementServices = paiementServices;
    }

    @PostMapping("/create")
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        return ResponseEntity.ok(paiementServices.createPaiement(paiement));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementServices.getAllPaiements());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable int id) {
        return paiementServices.getPaiementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable int id, @RequestBody Paiement paiementDetails) {
        return ResponseEntity.ok(paiementServices.updatePaiement(id, paiementDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaiement(@PathVariable int id) {
        paiementServices.deletePaiement(id);
        return ResponseEntity.ok("Paiement supprimé avec succès");
    }
}
