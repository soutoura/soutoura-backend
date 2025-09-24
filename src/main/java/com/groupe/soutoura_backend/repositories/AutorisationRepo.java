package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Autorisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorisationRepo extends JpaRepository<Autorisation, Integer> {
    List<Autorisation> findByUtilisateurId(int utilisateurId);
}
