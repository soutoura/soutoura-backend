package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
}
