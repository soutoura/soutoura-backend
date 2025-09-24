package com.groupe.soutoura_backend.controlers ;
import com.groupe.soutoura_backend.dto.RapportDto;
import com.groupe.soutoura_backend.dto.CreateRapportDto;
import com.groupe.soutoura_backend.enume.Type;
import com.groupe.soutoura_backend.services.RapportServices ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rapports")
public class RapportControlers {

    @Autowired
    private RapportServices rapportServices;

    @GetMapping
    public List<RapportDto> getAllRapports() {
        return rapportServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RapportDto> getRapportById(@PathVariable int id) {
        Optional<RapportDto> rapport = rapportServices.findById(id);
        return rapport.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enfant/{enfantId}")
    public List<RapportDto> getRapportsByEnfant(@PathVariable int enfantId) {
        return rapportServices.findByEnfantId(enfantId);
    }

    @GetMapping("/type/{type}")
    public List<RapportDto> getRapportsByType(@PathVariable Type type) {
        return rapportServices.findByType(type);
    }

    @GetMapping("/enfant/{enfantId}/type/{type}")
    public List<RapportDto> getRapportsByEnfantAndType(
            @PathVariable int enfantId,
            @PathVariable Type type) {
        return rapportServices.findByEnfantIdAndType(enfantId, type);
    }

    @PostMapping
    public ResponseEntity<RapportDto> createRapport(@RequestBody CreateRapportDto createDto) {
        try {
            RapportDto created = rapportServices.create(createDto);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RapportDto> updateRapport(@PathVariable int id,
                                                    @RequestBody RapportDto rapportDto) {
        Optional<RapportDto> updated = rapportServices.update(id, rapportDto);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRapport(@PathVariable int id) {
        if (rapportServices.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}