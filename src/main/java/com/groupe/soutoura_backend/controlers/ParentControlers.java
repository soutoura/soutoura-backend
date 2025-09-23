package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Parent;
import com.groupe.soutoura_backend.services.ParentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentControlers {
    private final ParentServices parentService;

    public ParentControlers(ParentServices parentService) {
        this.parentService = parentService;
    }
    @PostMapping
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        return ResponseEntity.ok(parentService.createParent(parent));
    }
    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents() {
        return ResponseEntity.ok(parentService.getAllParents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Parent> getParentById(@PathVariable int id) {
        return parentService.getParentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Parent> updateParent(@PathVariable int id, @RequestBody Parent parentDetails) {
        return ResponseEntity.ok(parentService.updateParent(id, parentDetails));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable int id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
