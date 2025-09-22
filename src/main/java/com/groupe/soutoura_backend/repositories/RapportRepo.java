package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.models.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportRepo extends JpaRepository<Rapport, Integer>{
}
