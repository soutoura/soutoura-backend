package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.enume.Role;
import com.groupe.soutoura_backend.models.Admin;
import com.groupe.soutoura_backend.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServices {
    private final AdminRepo adminRepository;

    @Value("${admin.email}")
    private String email;

    private String motDePasse = "soutoura@2025";
    public AdminServices(AdminRepo adminRepository){
        this.adminRepository = adminRepository;
    }
    // default admin
    public void Admin() {
        List<Admin> adminList = adminRepository.findAll();
        if (adminList.isEmpty()) {
            System.out.println("Création d'un admin");
            System.out.println(email);
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPrenom("Admin");
            admin.setNom("Soutoura");
            admin.setMotDePasse(BCrypt.hashpw(motDePasse, BCrypt.gensalt()));
            admin.setRole(Role.ADMIN);
            adminRepository.save(admin);
        }
    }
    // Créer un admin
    public Admin createAdmin(Admin admin) {
        admin.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));
        admin.setRole(Role.ADMIN);
        return adminRepository.save(admin);
    }
    // Lire tous les admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Lire un admin par id
    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    // Mettre à jour un admin
    public Admin updateAdmin(int id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin non trouvé"));

        admin.setNom(adminDetails.getNom()!=null ? adminDetails.getNom() : admin.getNom());
        admin.setPrenom(adminDetails.getPrenom()!=null ? adminDetails.getPrenom() : admin.getPrenom());
        admin.setEmail(adminDetails.getEmail()!=null ? adminDetails.getEmail() : admin.getEmail());
        admin.setMotDePasse(adminDetails.getMotDePasse());
        if (adminDetails.getMotDePasse() != null && !adminDetails.getMotDePasse().isEmpty())
            admin.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));
        admin.setRole(
                adminDetails.getRole()!=null ? Role.ADMIN: admin.getRole());

        return adminRepository.save(admin);
    }

    //Pour la mise a jour d'un administrateur :

    public ResponseEntity<?> updateAdmin(String email, Admin admin) {

        if (Objects.equals(email, ""))
            return ResponseEntity.badRequest().body("Veuillez entrer l'email");

        Admin existant = adminRepository.findByEmail(email).orElse(null);
        if (existant == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrateur introuvable");

        existant.setEmail(admin.getEmail() != null ? admin.getEmail() : existant.getEmail());
        existant.setRole(admin.getRole() != null ? admin.getRole() : existant.getRole());
        if (admin.getMotDePasse() != null && !admin.getMotDePasse().isEmpty())
            existant.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));

        Admin modifie = adminRepository.save(existant);
        return ResponseEntity.ok(modifie);
    }

    // Supprimer un admin
    public void deleteAdmin(int id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin non trouvé"));
        adminRepository.delete(admin);
    }
}
