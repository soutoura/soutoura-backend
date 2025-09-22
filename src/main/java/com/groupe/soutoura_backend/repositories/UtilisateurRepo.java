package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Integer> {
}
