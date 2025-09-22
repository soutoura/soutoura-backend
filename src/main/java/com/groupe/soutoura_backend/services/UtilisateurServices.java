package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.dto.RequestUtilisateur;
import com.groupe.soutoura_backend.enume.Role;
import com.groupe.soutoura_backend.models.Association;
import com.groupe.soutoura_backend.models.Parent;
import com.groupe.soutoura_backend.models.Parrain;
import com.groupe.soutoura_backend.models.Utilisateur;
import com.groupe.soutoura_backend.repositories.AssociationRepo;
import com.groupe.soutoura_backend.repositories.ParentRepo;
import com.groupe.soutoura_backend.repositories.ParrainRepo;
import com.groupe.soutoura_backend.repositories.UtilisateurRepo;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UtilisateurServices {
    private final UtilisateurRepo utilisateurRepository;
    private final ParrainRepo parrainRepo;
    private final AssociationRepo associationRepository;
    private final ParentRepo parentRepository;


    public UtilisateurServices(UtilisateurRepo utilisateurRepository,
                               ParrainRepo parrainRepo,
                               AssociationRepo associationRepository,
                               ParentRepo parentRepository
    ){
        this.utilisateurRepository = utilisateurRepository;
        this.parrainRepo = parrainRepo;
        this.associationRepository = associationRepository;
        this.parentRepository = parentRepository;
    }


    public Utilisateur register(RequestUtilisateur utilisateur) {
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        Utilisateur user = new Utilisateur();
        user.setNom(utilisateur.getNom());
        user.setPrenom(utilisateur.getPrenom());
        user.setEmail(utilisateur.getEmail());
        user.setMotDePasse(BCrypt.hashpw((utilisateur.getMotDePasse()),BCrypt.gensalt())); // hashage
        user.setTelephone(utilisateur.getTelephone());
        user.setRole(utilisateur.getRole());
        user.setEstActif(false);
        user.setDateCreation(new Date());

        Utilisateur savedUser = utilisateurRepository.save(user);

        // Si c'est un Parrain, créer aussi l'entité Parrain
        switch (utilisateur.getRole()) {
            case PARRAIN -> {
                Parrain parrain = new Parrain();
                parrain.setPays(utilisateur.getPays());
                parrain.setUtilisateur(user);
                parrainRepo.save(parrain);
            }
            case ASSOCIATION -> {
                Association association = new Association();
                association.setNomAssociation(utilisateur.getNomAssociation()); //ce champs doit être enlever
                association.setAdresse(utilisateur.getAdresse());     // à adapter
                association.setPapierAssociation(utilisateur.getPapierAssociation()); // à adapter
                association.setUtilisateur(user);
                associationRepository.save(association);
            }
            case PARENT -> {
                Parent parent = new Parent();
                parent.setUtilisateur(user);
                parentRepository.save(parent);
            }
            case ADMIN -> {
                // Admin = juste l’utilisateur, pas d’entité spécifique
            }
        }



        return savedUser;
    }
}
