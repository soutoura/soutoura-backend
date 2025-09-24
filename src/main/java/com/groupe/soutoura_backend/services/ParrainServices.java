package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.dto.ParrainDto;
import com.groupe.soutoura_backend.dto.CreateParrainDto;
import com.groupe.soutoura_backend.models.Parrain;
import com.groupe.soutoura_backend.models.Utilisateur;
import com.groupe.soutoura_backend.enume.Role;
import com.groupe.soutoura_backend.repositories.ParrainRepo;
import com.groupe.soutoura_backend.repositories.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParrainServices {

    @Autowired
    private ParrainRepo parrainRepository;

    @Autowired
    private UtilisateurRepo utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<ParrainDto> findAll() {
        return parrainRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ParrainDto> findById(int id) {
        return parrainRepository.findById(id).map(this::convertToDto);
    }

    public ParrainDto create(CreateParrainDto createDto) {
        if (utilisateurRepository.existsByEmail(createDto.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Créer l'utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(createDto.getNom());
        utilisateur.setPrenom(createDto.getPrenom());
        utilisateur.setEmail(createDto.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(createDto.getMotDePasse()));
        utilisateur.setTelephone(createDto.getTelephone());
        utilisateur.setEstActif(true);
        utilisateur.setDateCreation(new Date());
        utilisateur.setRole(Role.PARRAIN);

        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);

        // Créer le parrain
        Parrain parrain = new Parrain();
        parrain.setPays(createDto.getPays());
        parrain.setUtilisateur(savedUtilisateur);

        Parrain savedParrain = parrainRepository.save(parrain);
        return convertToDto(savedParrain);
    }

    public Optional<ParrainDto> update(int id, String pays) {
        return parrainRepository.findById(id).map(existing -> {
            existing.setPays(pays);
            Parrain updated = parrainRepository.save(existing);
            return convertToDto(updated);
        });
    }

    public boolean delete(int id) {
        if (parrainRepository.existsById(id)) {
            parrainRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ParrainDto> findByUtilisateurId(int userId) {
        return parrainRepository.findByUtilisateurId(userId).map(this::convertToDto);
    }

    private ParrainDto convertToDto(Parrain parrain) {
        return new ParrainDto(parrain.getId(), parrain.getPays(), parrain.getUtilisateur());
    }
}