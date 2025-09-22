package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Depense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepenseRepo extends JpaRepository<Depense, Integer> {
}
