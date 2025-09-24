package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Rapport;
import com.groupe.soutoura_backend.repositories.RapportRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RapportServices {
    private final RapportRepo rapportRepository;

    public RapportServices(RapportRepo rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    public Rapport createRapport(Rapport rapport) {
        return rapportRepository.save(rapport);
    }

    public List<Rapport> getAllRapports() {
        return rapportRepository.findAll();
    }

    public Optional<Rapport> getRapportById(int id) {
        return rapportRepository.findById(id);
    }

    public Rapport updateRapport(int id, Rapport rapportDetails) {
        Rapport rapport = rapportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport non trouvé avec id : " + id));

        rapport.setType(rapportDetails.getType()!= null ?
                rapportDetails.getType() : rapport.getType());
        rapport.setPeriode(
                rapportDetails.getPeriode() != null ?
                       rapportDetails.getPeriode() : rapport.getPeriode()
        );
        rapport.setContenu(rapportDetails.getContenu()!= null ?
                rapportDetails.getContenu() : rapport.getContenu());
        rapport.setDateCreation(rapportDetails.getDateCreation());
        rapport.setEnfant(rapportDetails.getEnfant() != null ?
                        rapportDetails.getEnfant() : rapport.getEnfant());
        return rapportRepository.save(rapport);
    }

    public void deleteRapport(int id) {
        rapportRepository.deleteById(id);
    }
}
