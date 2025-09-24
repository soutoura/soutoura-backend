package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.dto.RapportDto;
import com.groupe.soutoura_backend.dto.CreateRapportDto;
import com.groupe.soutoura_backend.models.Rapport;
import com.groupe.soutoura_backend.models.Enfant;
import com.groupe.soutoura_backend.enume.Type;
import com.groupe.soutoura_backend.repositories.RapportRepo;
import com.groupe.soutoura_backend.repositories.EnfantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RapportServices {

    @Autowired
    private RapportRepo rapportRepo;

    @Autowired
    private EnfantRepo enfantRepo;

    public List<RapportDto> findAll() {
        return rapportRepo.findByOrderByDateCreationDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<RapportDto> findById(int id) {
        return rapportRepo.findById(id).map(this::convertToDto);
    }

    public List<RapportDto> findByEnfantId(int enfantId) {
        return rapportRepo.findByEnfantId(enfantId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<RapportDto> findByType(Type type) {
        return rapportRepo.findByType(type).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<RapportDto> findByEnfantIdAndType(int enfantId, Type type) {
        return rapportRepo.findByEnfantIdAndType(enfantId, type).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RapportDto create(CreateRapportDto createDto) {
        Enfant enfant = enfantRepo.findById(createDto.getEnfantId())
                .orElseThrow(() -> new RuntimeException("Enfant non trouvé"));

        Rapport rapport = new Rapport();
        rapport.setType(createDto.getType());
        rapport.setPeriode(createDto.getPeriode());
        rapport.setContenu(createDto.getContenu());
        rapport.setDateCreation(new Date());
        rapport.setEnfant(enfant);

        Rapport saved = rapportRepo.save(rapport);
        return convertToDto(saved);
    }

    public Optional<RapportDto> update(int id, RapportDto rapportDto) {
        return rapportRepo.findById(id).map(existing -> {
            existing.setType(rapportDto.getType());
            existing.setPeriode(rapportDto.getPeriode());
            existing.setContenu(rapportDto.getContenu());
            Rapport updated = rapportRepo.save(existing);
            return convertToDto(updated);
        });
    }

    public boolean delete(int id) {
        if (rapportRepo.existsById(id)) {
            rapportRepo.deleteById(id);
            return true;
        }
        return false;
    }

    private RapportDto convertToDto(Rapport rapport) {
        return new RapportDto(
                rapport.getId(),
                rapport.getType(),
                rapport.getPeriode(),
                rapport.getContenu(),
                rapport.getDateCreation(),
                rapport.getEnfant().getId(),
                rapport.getEnfant().getNom() // Supposant que Enfant a un champ nom
        );
    }
}