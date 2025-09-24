package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
}