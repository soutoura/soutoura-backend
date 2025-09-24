package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Paiement;
import com.groupe.soutoura_backend.repositories.PaiementRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementServices {
    private final PaiementRepo paiementRepo;

    public PaiementServices(PaiementRepo paiementRepo)
    {
        this.paiementRepo = paiementRepo;
    }
    public Paiement createPaiement(Paiement paiement) {
        return paiementRepo.save(paiement);
    }
    public List<Paiement> getAllPaiements() {
        return paiementRepo.findAll();
    }
    public Optional<Paiement> getPaiementById(int id) {
        return paiementRepo.findById(id);
    }
    public Paiement updatePaiement(int id, Paiement paiementDetails) {
        Paiement paiement = paiementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));

        paiement.setMontant(paiementDetails.getMontant() != null ? paiementDetails.getMontant() : paiement.getMontant());
        paiement.setDatePaiement(paiementDetails.getDatePaiement() != null ? paiementDetails.getDatePaiement() : paiement.getDatePaiement());
        paiement.setModePaiement(paiementDetails.getModePaiement() != null ? paiementDetails.getModePaiement() : paiement.getModePaiement());
        paiement.setStatut(paiementDetails.getStatut() != null ? paiementDetails.getStatut() : paiement.getStatut());
        paiement.setParrainage(paiementDetails.getParrainage() != null ? paiementDetails.getParrainage() : paiement.getParrainage());

        return paiementRepo.save(paiement);
    }
    public void deletePaiement(int id) {
        Paiement paiement = paiementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        paiementRepo.delete(paiement);
    }
}
