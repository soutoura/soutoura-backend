package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.RapportPedagogique;
import com.groupe.soutoura_backend.services.RapportPedagogiqueServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rapporpedagogiques")
public class RapportPedagogiqueControlers {
    private final RapportPedagogiqueServices rapportPedagogiqueService;

    public RapportPedagogiqueControlers(RapportPedagogiqueServices rapportPedagogiqueService) {
        this.rapportPedagogiqueService = rapportPedagogiqueService;
    }

    @PostMapping
    public ResponseEntity<RapportPedagogique> create(@RequestBody RapportPedagogique rapport) {
        return ResponseEntity.ok(rapportPedagogiqueService.create(rapport));
    }

    @GetMapping
    public ResponseEntity<List<RapportPedagogique>> getAll() {
        return ResponseEntity.ok(rapportPedagogiqueService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RapportPedagogique> getById(@PathVariable int id) {
        return rapportPedagogiqueService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enfant/{enfantId}")
    public ResponseEntity<List<RapportPedagogique>> getByEnfant(@PathVariable int enfantId) {
        return ResponseEntity.ok(rapportPedagogiqueService.getByEnfant(enfantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RapportPedagogique> update(@PathVariable int id, @RequestBody RapportPedagogique rapport) {
        return ResponseEntity.ok(rapportPedagogiqueService.update(id, rapport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rapportPedagogiqueService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
