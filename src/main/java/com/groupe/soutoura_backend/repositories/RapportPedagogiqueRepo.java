package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.RapportPedagogique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RapportPedagogiqueRepo extends JpaRepository<RapportPedagogique, Integer> {
    // Récupérer tous les rapports pédagogiques d’un enfant
    List<RapportPedagogique> findByEnfantId(int enfantId);
}
