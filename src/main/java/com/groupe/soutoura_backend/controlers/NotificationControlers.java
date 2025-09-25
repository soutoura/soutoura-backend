package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Notification;
import com.groupe.soutoura_backend.services.NotificationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationControlers {
    private final NotificationServices notificationServices;

    public NotificationControlers(NotificationServices notificationServices) {
        this.notificationServices = notificationServices;
    }

    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(
            @RequestParam int userId,
            @RequestParam String message,
            @RequestParam String type) {

        Notification notification = notificationServices.envoyerNotification(userId, message, type);
        return ResponseEntity.ok(notification);
    }

    @PostMapping("/read/{notificationId}")
    public ResponseEntity<Void> markAsRead(@PathVariable int notificationId) {

        notificationServices.marquerCommeLue(notificationId);
        return ResponseEntity.ok().build();
    }

    // Récupérer les notifications d’un utilisateur par son ID
    @GetMapping("/user")
    public ResponseEntity<List<Notification>> getNotifications(@RequestParam int userId) {
        List<Notification> notifications = notificationServices.getNotifications(userId);
        return ResponseEntity.ok(notifications);
    }
}
