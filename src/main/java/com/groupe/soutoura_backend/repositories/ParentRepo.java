package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepo extends JpaRepository<Parent, Integer> {
}
