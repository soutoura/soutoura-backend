package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.dto.RequestUtilisateur;
import com.groupe.soutoura_backend.models.Utilisateur;
import com.groupe.soutoura_backend.services.UtilisateurServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurControlers {
    private final UtilisateurServices utilisateurServices;

    public UtilisateurControlers(UtilisateurServices utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@RequestBody RequestUtilisateur utilisateur) {
        Utilisateur savedUser = utilisateurServices.register(utilisateur);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll() {
        return ResponseEntity.ok(utilisateurServices.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable int id) {
        return ResponseEntity.ok(utilisateurServices.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable int id, @RequestBody RequestUtilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurServices.update(id, utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        utilisateurServices.delete(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès !");
    }
}
