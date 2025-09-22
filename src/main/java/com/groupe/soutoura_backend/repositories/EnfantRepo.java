package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfantRepo extends JpaRepository<Enfant, Integer> {
}
