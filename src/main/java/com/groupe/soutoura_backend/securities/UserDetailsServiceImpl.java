package com.groupe.soutoura_backend.securities;


import com.groupe.soutoura_backend.models.Admin;
import com.groupe.soutoura_backend.models.UserDetailsImpl;
import com.groupe.soutoura_backend.models.Utilisateur;
import com.groupe.soutoura_backend.repositories.AdminRepo;
import com.groupe.soutoura_backend.repositories.UtilisateurRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepo adminRepo;
    private final UtilisateurRepo utilisateurRepo;

    public UserDetailsServiceImpl(AdminRepo adminRepo, UtilisateurRepo utilisateurRepo) {
        this.adminRepo = adminRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByEmail(email).orElse(null);
        if (admin != null) return new UserDetailsImpl(admin);

        Utilisateur user = utilisateurRepo.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Utilisateur introuvable avec email : " + email)
        );
        return new UserDetailsImpl(user);
    }
}
