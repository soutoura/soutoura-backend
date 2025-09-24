package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Autorisation;
import com.groupe.soutoura_backend.repositories.AutorisationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorisationServices {
    private final AutorisationRepo autorisationRepo;

    public AutorisationServices(AutorisationRepo autorisationRepo) {
        this.autorisationRepo = autorisationRepo;
    }

    public Autorisation createAutorisation(Autorisation autorisation) {
        return autorisationRepo.save(autorisation);
    }

    public Autorisation updateAutorisation(int id, Autorisation autorisationDetails) {
        Autorisation autorisation = autorisationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée avec id " + id));

        autorisation.setDateDebut(autorisationDetails.getDateDebut()!= null
                ? autorisationDetails.getDateDebut() : autorisation.getDateDebut()
        );
        autorisation.setDateFin(autorisationDetails.getDateFin()
                != null
                ? autorisationDetails.getDateFin() : autorisation.getDateFin());
        autorisation.setStatut(autorisationDetails.getStatut()
                != null
                ? autorisationDetails.getStatut() : autorisation.getStatut());

        autorisation.setRapportPedagogique(autorisationDetails.getRapportPedagogique()
                != null
                ? autorisationDetails.getRapportPedagogique()
                : autorisation.getRapportPedagogique());

        autorisation.setUtilisateur(autorisationDetails.getUtilisateur()!=null
        ? autorisationDetails.getUtilisateur()
                :autorisation.getUtilisateur());

        return autorisationRepo.save(autorisation);
    }

    public void deleteAutorisation(int id) {
        autorisationRepo.deleteById(id);
    }

    public Optional<Autorisation> getAutorisationById(int id) {
        return autorisationRepo.findById(id);
    }

    public List<Autorisation> getAllAutorisations() {
        return autorisationRepo.findAll();
    }
}
