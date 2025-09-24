package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.RapportPedagogique;
import com.groupe.soutoura_backend.repositories.RapportPedagogiqueRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RapportPedagogiqueServices {
    private final RapportPedagogiqueRepo rapportPedagogiqueRepo;

    public RapportPedagogiqueServices(RapportPedagogiqueRepo rapportPedagogiqueRepo) {
        this.rapportPedagogiqueRepo = rapportPedagogiqueRepo;
    }

    public RapportPedagogique create(RapportPedagogique rapport) {
        return rapportPedagogiqueRepo.save(rapport);
    }

    public List<RapportPedagogique> getAll() {
        return rapportPedagogiqueRepo.findAll();
    }

    public Optional<RapportPedagogique> getById(int id) {
        return rapportPedagogiqueRepo.findById(id);
    }

    public List<RapportPedagogique> getByEnfant(int enfantId) {
        return rapportPedagogiqueRepo.findByEnfantId(enfantId);
    }

    public RapportPedagogique update(int id, RapportPedagogique rapportDetails) {
        RapportPedagogique rapport = rapportPedagogiqueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport pédagogique non trouvé"));

        rapport.setPeriode(rapportDetails.getPeriode() != null ? rapportDetails.getPeriode() : rapport.getPeriode());
        rapport.setAbsence(rapportDetails.getAbsence());
        rapport.setMoyenneFinale(rapportDetails.getMoyenneFinale());
        rapport.setBulletinPdf(rapportDetails.getBulletinPdf() != null ? rapportDetails.getBulletinPdf() : rapport.getBulletinPdf());
        rapport.setDateCreation(rapportDetails.getDateCreation() != null ? rapportDetails.getDateCreation() : rapport.getDateCreation());

        return rapportPedagogiqueRepo.save(rapport);
    }

    public void delete(int id) {
        RapportPedagogique rapport = rapportPedagogiqueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rapport pédagogique non trouvé"));
        rapportPedagogiqueRepo.delete(rapport);
    }

}
