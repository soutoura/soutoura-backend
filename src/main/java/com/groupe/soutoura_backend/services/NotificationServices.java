package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.dto.NotificationDto;
import com.groupe.soutoura_backend.dto.CreateNotificationDto;
import com.groupe.soutoura_backend.models.Notification;
import com.groupe.soutoura_backend.models.Utilisateur;
import com.groupe.soutoura_backend.repositories.NotificationRepo;
import com.groupe.soutoura_backend.repositories.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServices {

    @Autowired
    private NotificationRepo notificationRepository;

    @Autowired
    private UtilisateurRepo utilisateurRepository;

    public List<NotificationDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<NotificationDto> findById(int id) {
        return notificationRepository.findById(id).map(this::convertToDto);
    }

    public List<NotificationDto> findByUtilisateurId(int utilisateurId) {
        return notificationRepository.findByUtilisateurId(utilisateurId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<NotificationDto> findNonLuesByUtilisateurId(int utilisateurId) {
        return notificationRepository.findByUtilisateurIdAndEstLusFalse(utilisateurId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Long countNonLuesByUtilisateurId(int utilisateurId) {
        return notificationRepository.countByUtilisateurIdAndEstLusFalse(utilisateurId);
    }

    public NotificationDto create(CreateNotificationDto createDto) {
        Utilisateur utilisateur = utilisateurRepository.findById(createDto.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Notification notification = new Notification();
        notification.setMessage(createDto.getMessage());
        notification.setType(createDto.getType());
        notification.setDateEnvoie(new Date());
        notification.setEstLus(false);
        notification.setUtilisateur(utilisateur);

        Notification saved = notificationRepository.save(notification);
        return convertToDto(saved);
    }

    public Optional<NotificationDto> update(int id, NotificationDto notificationDto) {
        return notificationRepository.findById(id).map(existing -> {
            existing.setMessage(notificationDto.getMessage());
            existing.setType(notificationDto.getType());
            existing.setEstLus(notificationDto.isEstLus());
            Notification updated = notificationRepository.save(existing);
            return convertToDto(updated);
        });
    }

    public boolean marquerCommeLue(int id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            notificationRepository.marquerCommeLue(id);
            return true;
        }
        return false;
    }

    public void marquerToutesCommeLues(int utilisateurId) {
        notificationRepository.marquerToutesCommeLues(utilisateurId);
    }

    public boolean delete(int id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private NotificationDto convertToDto(Notification notification) {
        return new NotificationDto(
                notification.getId(),
                notification.getMessage(),
                notification.getDateEnvoie(),
                notification.isEstLus(),
                notification.getType(),
                notification.getUtilisateur().getId(),
                notification.getUtilisateur().getNom() + " " + notification.getUtilisateur().getPrenom()
        );
    }
}