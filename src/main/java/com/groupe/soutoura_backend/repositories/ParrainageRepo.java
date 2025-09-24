package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Parrainage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParrainageRepo extends JpaRepository<Parrainage, Integer> {
    //recuperer les parrainages d'un parrain
    List<Parrainage> findByParrainId(int parrainId);
    //recuperer les parrainages d'un enfant
    List<Parrainage> findByEnfantId(int enfantId);
}
