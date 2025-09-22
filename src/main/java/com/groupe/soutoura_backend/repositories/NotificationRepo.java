package com.groupe.soutoura_backend.repositories;

import com.groupe.soutoura_backend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {
}
