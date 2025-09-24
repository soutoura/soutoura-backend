package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Parrainage;
import com.groupe.soutoura_backend.services.ParrainageServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parrainages")
public class ParrainageControlers {
    private final ParrainageServices parrainageServices;

    public ParrainageControlers(ParrainageServices parrainageServices) {
        this.parrainageServices = parrainageServices;
    }

    @PostMapping("/create")
    public ResponseEntity<Parrainage> createParrainage(@RequestBody Parrainage parrainage) {
        return ResponseEntity.ok(parrainageServices.createParrainage(parrainage));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Parrainage>> getAllParrainages() {
        return ResponseEntity.ok(parrainageServices.getAllParrainages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parrainage> getParrainageById(@PathVariable int id) {
        return parrainageServices.getParrainageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Parrainage> updateParrainage(@PathVariable int id, @RequestBody Parrainage parrainageDetails) {
        return ResponseEntity.ok(parrainageServices.updateParrainage(id, parrainageDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteParrainage(@PathVariable int id) {
        parrainageServices.deleteParrainage(id);
        return ResponseEntity.ok("Parrainage supprimé avec succès");
    }

    //lister les parrainages d'un parrain donné
    @GetMapping("/parrains/id")
    public List<Parrainage> getParrainParrainage(@PathVariable int id)
    {
        return parrainageServices.getListParrainageParrains(id);
    }

    //lister les parrainages d'un enfant donné
    @GetMapping("/enfants/id")
    public List<Parrainage> getEnfantsParrainage(@PathVariable int id)
    {
        return parrainageServices.getListParrainageEnfants(id);
    }

}
