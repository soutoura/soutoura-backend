package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.enume.Status;
import com.groupe.soutoura_backend.models.Enfant;
import com.groupe.soutoura_backend.models.Parrain;
import com.groupe.soutoura_backend.models.Parrainage;
import com.groupe.soutoura_backend.repositories.EnfantRepo;
import com.groupe.soutoura_backend.repositories.ParrainRepo;
import com.groupe.soutoura_backend.repositories.ParrainageRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParrainageServices {
    private final ParrainageRepo parrainageRepo;
    private final ParrainRepo parrainRepository;
    private final EnfantRepo enfantRepository;

    public ParrainageServices(ParrainageRepo parrainageRepo,
                              ParrainRepo parrainRepository,
                              EnfantRepo enfantRepository)
    {
        this.parrainageRepo = parrainageRepo;
        this.enfantRepository = enfantRepository;
        this.parrainRepository = parrainRepository;
    }

    public Parrainage createParrainage(Parrainage parrainage) {
        return parrainageRepo.save(parrainage);
    }

    public Parrainage creerParrainage(int parrainId, int enfantId) {
        Parrain parrain = parrainRepository.findById(parrainId)
                .orElseThrow(() -> new RuntimeException("Parrain introuvable"));

        Enfant enfant = enfantRepository.findById(enfantId)
                .orElseThrow(() -> new RuntimeException("Enfant introuvable"));

        Parrainage parrainage = new Parrainage();
        parrainage.setParrain(parrain);
        parrainage.setEnfant(enfant);
        parrainage.setDateDebut(new Date());
        parrainage.setStatus(Status.ENATTENTE); // ou en attente selon ta logique

        return parrainageRepo.save(parrainage);
    }

    public List<Parrainage> getAllParrainages() {
        return parrainageRepo.findAll();
    }
    public Parrainage updateParrainage(int id, Parrainage parrainageDetails) {
        Parrainage parrainage = parrainageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parrainage non trouvé"));

        parrainage.setDateDebut(parrainageDetails.getDateDebut() != null ? parrainageDetails.getDateDebut() : parrainage.getDateDebut());
        parrainage.setDateFin(parrainageDetails.getDateFin() != null ? parrainageDetails.getDateFin() : parrainage.getDateFin());
        parrainage.setStatus(parrainageDetails.getStatus() != null ? parrainageDetails.getStatus() : parrainage.getStatus());
        parrainage.setParrain(parrainageDetails.getParrain() != null ? parrainageDetails.getParrain() : parrainage.getParrain());
        parrainage.setEnfant(parrainageDetails.getEnfant() != null ? parrainageDetails.getEnfant() : parrainage.getEnfant());

        return parrainageRepo.save(parrainage);
    }
    public void deleteParrainage(int id) {
        Parrainage parrainage = parrainageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parrainage non trouvé"));
        parrainageRepo.delete(parrainage);
    }

    public Optional<Parrainage> getParrainageById(int id) {
        return parrainageRepo.findById(id);
    }

    public List<Parrainage> getListParrainageParrains(int idParrain){
        return parrainageRepo.findByParrainId(idParrain);
    }

    public List<Parrainage> getListParrainageEnfants(int idEnfant){
        return parrainageRepo.findByEnfantId(idEnfant);
    }
}
