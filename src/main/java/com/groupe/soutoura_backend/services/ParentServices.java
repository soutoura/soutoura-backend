package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.models.Parent;
import com.groupe.soutoura_backend.repositories.ParentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentServices {
    private final ParentRepo parentRepo;

    public ParentServices(ParentRepo parentRepo) {
        this.parentRepo = parentRepo;
    }

    public Parent createParent(Parent parent) {
        return parentRepo.save(parent);
    }

    public List<Parent> getAllParents() {
        return parentRepo.findAll();
    }

    public Optional<Parent> getParentById(int id) {
        return parentRepo.findById(id);
    }

    public Parent updateParent(int id, Parent parentDetails) {
        Parent parent = parentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent non trouvé avec id: " + id));

        parent.setAdresse(parentDetails.getAdresse()!=null ? parentDetails.getAdresse()
                : parent.getAdresse()
        );
        parent.setGenre(parentDetails.getGenre() != null ?
                parentDetails.getGenre(): parent.getGenre());

        return parentRepo.save(parent);
    }
    public void deleteParent(int id) {
        Parent parent = parentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent non trouvé avec id: " + id));
        parentRepo.delete(parent);
    }

}
