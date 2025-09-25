package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Autorisation;
import com.groupe.soutoura_backend.services.AutorisationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autorisations")
public class AutorisationControlers {
    private final AutorisationServices autorisationService;

    public AutorisationControlers(AutorisationServices autorisationService) {
        this.autorisationService = autorisationService;
    }
    @PostMapping
    public ResponseEntity<Autorisation> createAutorisation(@RequestBody Autorisation autorisation) {
        return ResponseEntity.ok(autorisationService.createAutorisation(autorisation));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Autorisation> updateAutorisation(@PathVariable int id,
                                                           @RequestBody Autorisation autorisation) {
        return ResponseEntity.ok(autorisationService.updateAutorisation(id, autorisation));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutorisation(@PathVariable int id) {
        autorisationService.deleteAutorisation(id);
        return ResponseEntity.noContent().build();
    }


}
