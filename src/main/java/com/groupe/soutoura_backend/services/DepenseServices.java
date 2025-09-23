package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Depense;
import com.groupe.soutoura_backend.repositories.DepenseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepenseServices {
    private final DepenseRepo depenseRepository;


    public DepenseServices(DepenseRepo depenseRepository) {
        this.depenseRepository = depenseRepository;
    }

    public Depense create(Depense depense) {
        return depenseRepository.save(depense);
    }

    public List<Depense> getAll() {
        return depenseRepository.findAll();
    }

    public Depense getById(int id) {
        return depenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense introuvable avec ID: " + id));
    }

    public Depense update(int id, Depense newDepense) {
        Depense existing = getById(id);

        existing.setIntitule(newDepense.getIntitule()!= null ? newDepense.getIntitule()
                : existing.getIntitule());
        existing.setMontant(newDepense.getMontant()!= null ? newDepense.getMontant()
                : existing.getMontant());
        existing.setDateDepense(newDepense.getDateDepense()!= null ? newDepense.getDateDepense()
                : existing.getDateDepense());
        existing.setJustificatif(newDepense.getJustificatif()
                != null ? newDepense.getJustificatif()
                : existing.getJustificatif()
        );
        existing.setCategorie(newDepense.getCategorie()
                != null ? newDepense.getCategorie()
                : existing.getCategorie()
        );
        existing.setEnfant(newDepense.getEnfant()!= null ? newDepense.getEnfant()
                : existing.getEnfant());

        return depenseRepository.save(existing);
    }

    public void delete(int id) {
        depenseRepository.deleteById(id);
    }



}
