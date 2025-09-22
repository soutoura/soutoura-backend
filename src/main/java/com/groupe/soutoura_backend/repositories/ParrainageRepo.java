package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Parrainage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParrainageRepo extends JpaRepository<Parrainage, Integer> {
}
