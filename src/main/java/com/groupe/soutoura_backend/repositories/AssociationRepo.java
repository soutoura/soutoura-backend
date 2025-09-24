package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.models.Autorisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssociationRepo extends JpaRepository<Association, Integer> {

    Optional<Association> findByUtilisateur_Id(int utilisateurId);

}
