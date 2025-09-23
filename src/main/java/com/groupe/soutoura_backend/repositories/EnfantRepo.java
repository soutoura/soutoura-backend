package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnfantRepo extends JpaRepository<Enfant, Integer> {
    List<Enfant> findByParentId(int parentId);
    List<Enfant> findByAssociationId(int associationId);
}
