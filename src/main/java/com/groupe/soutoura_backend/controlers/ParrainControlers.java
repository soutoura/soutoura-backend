package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.services.ParrainServices;
import org.springframework.web.bind.annotation.RestController;

import com.groupe.soutoura_backend.dto.ParrainDto;
import com.groupe.soutoura_backend.dto.CreateParrainDto;
import com.groupe.soutoura_backend.services.ParrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parrains")
@CrossOrigin(origins = "http://localhost:3000")
public class ParrainControlers {

    @Autowired
    private ParrainServices parrainServices;

    @GetMapping
    public List<ParrainDto> getAllParrains() {
        return parrainServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParrainDto> getParrainById(@PathVariable int id) {
        Optional<ParrainDto> parrain = parrainServices.findById(id);
        return parrain.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity<ParrainDto> getParrainByUtilisateurId(@PathVariable int userId) {
        Optional<ParrainDto> parrain = parrainServices.findByUtilisateurId(userId);
        return parrain.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createParrain(@RequestBody CreateParrainDto createDto) {
        try {
            ParrainDto created = parrainServices.create(createDto);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParrainDto> updateParrain(@PathVariable int id,
                                                    @RequestBody String pays) {
        Optional<ParrainDto> updated = parrainServices.update(id, pays);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParrain(@PathVariable int id) {
        if (parrainServices.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}