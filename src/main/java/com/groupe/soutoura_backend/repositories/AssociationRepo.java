package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.models.Autorisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociationRepo extends JpaRepository<Association, Integer> {

}
