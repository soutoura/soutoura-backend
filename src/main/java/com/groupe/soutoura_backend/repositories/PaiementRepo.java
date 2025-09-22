package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepo extends JpaRepository<Paiement, Integer> {
}
