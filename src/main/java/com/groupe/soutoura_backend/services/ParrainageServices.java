package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Parrainage;
import com.groupe.soutoura_backend.repositories.ParrainageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParrainageServices {
    private final ParrainageRepo parrainageRepo;

    public ParrainageServices(ParrainageRepo parrainageRepo) {
        this.parrainageRepo = parrainageRepo;
    }

    public Parrainage createParrainage(Parrainage parrainage) {
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
