package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Rapport;
import com.groupe.soutoura_backend.enume.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RapportRepo extends JpaRepository<Rapport, Integer> {

    List<Rapport> findByEnfantId(int enfantId);

    List<Rapport> findByType(Type type);

    List<Rapport> findByEnfantIdAndType(int enfantId, Type type);

    @Query("SELECT r FROM Rapport r WHERE r.periode LIKE %:periode%")
    List<Rapport> findByPeriodeContaining(@Param("periode") String periode);

    List<Rapport> findByOrderByDateCreationDesc();
}