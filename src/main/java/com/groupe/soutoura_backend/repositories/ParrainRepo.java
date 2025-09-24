package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Parrain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ParrainRepo extends JpaRepository<Parrain, Integer> {
    Optional<Parrain> findByUtilisateurId(int utilisateurId);
    boolean existsByUtilisateurId(int utilisateurId);
}