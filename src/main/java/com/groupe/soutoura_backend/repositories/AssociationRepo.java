package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Association;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationRepo extends JpaRepository<Association, Integer> {
}
