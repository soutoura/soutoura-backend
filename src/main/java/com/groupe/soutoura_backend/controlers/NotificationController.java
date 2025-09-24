package com.groupe.soutoura_backend.controlers;


import com.groupe.soutoura_backend.dto.NotificationDto;
import com.groupe.soutoura_backend.dto.CreateNotificationDto;
import com.groupe.soutoura_backend.services.NotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    @Autowired
    private NotificationServices notificationService;

    // GET /api/notifications
    @GetMapping
    public List<NotificationDto> getAllNotifications() {
        return notificationService.findAll();
    }

    // GET /api/notifications/{id}
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable int id) {
        Optional<NotificationDto> notification = notificationService.findById(id);
        return notification.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/notifications/utilisateur/{userId}
    @GetMapping("/utilisateur/{userId}")
    public List<NotificationDto> getNotificationsByUtilisateur(@PathVariable int userId) {
        return notificationService.findByUtilisateurId(userId);
    }

    // GET /api/notifications/utilisateur/{userId}/non-lues
    @GetMapping("/utilisateur/{userId}/non-lues")
    public List<NotificationDto> getNotificationsNonLues(@PathVariable int userId) {
        return notificationService.findNonLuesByUtilisateurId(userId);
    }

    // GET /api/notifications/utilisateur/{userId}/count-non-lues
    @GetMapping("/utilisateur/{userId}/count-non-lues")
    public Long countNotificationsNonLues(@PathVariable int userId) {
        return notificationService.countNonLuesByUtilisateurId(userId);
    }

    // POST /api/notifications
    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody CreateNotificationDto createDto) {
        try {
            NotificationDto created = notificationService.create(createDto);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/notifications/{id}
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable int id,
                                                              @RequestBody NotificationDto notificationDto) {
        Optional<NotificationDto> updated = notificationService.update(id, notificationDto);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/notifications/{id}/marquer-lue
    @PutMapping("/{id}/marquer-lue")
    public ResponseEntity<Void> marquerCommeLue(@PathVariable int id) {
        if (notificationService.marquerCommeLue(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // PUT /api/notifications/utilisateur/{userId}/marquer-toutes-lues
    @PutMapping("/utilisateur/{userId}/marquer-toutes-lues")
    public ResponseEntity<Void> marquerToutesCommeLues(@PathVariable int userId) {
        notificationService.marquerToutesCommeLues(userId);
        return ResponseEntity.ok().build();
    }

    // DELETE /api/notifications/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
        if (notificationService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}