package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Depense;
import com.groupe.soutoura_backend.services.DepenseServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depenses")
public class DepenseControlers {
    private final DepenseServices depenseService;


    public DepenseControlers(DepenseServices depenseService) {
        this.depenseService = depenseService;
    }

    @PostMapping
    public ResponseEntity<Depense> create(@RequestBody Depense depense) {
        return ResponseEntity.ok(depenseService.create(depense));
    }

    @GetMapping
    public ResponseEntity<List<Depense>> getAll() {
        return ResponseEntity.ok(depenseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Depense> getById(@PathVariable int id) {
        return ResponseEntity.ok(depenseService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Depense> update(@PathVariable int id, @RequestBody Depense depense) {
        return ResponseEntity.ok(depenseService.update(id, depense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        depenseService.delete(id);
        return ResponseEntity.ok("Dépense supprimée avec succès !");
    }
}
