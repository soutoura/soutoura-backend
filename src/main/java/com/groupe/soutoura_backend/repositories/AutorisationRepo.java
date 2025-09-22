package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Autorisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorisationRepo extends JpaRepository<Autorisation, Integer> {
}
