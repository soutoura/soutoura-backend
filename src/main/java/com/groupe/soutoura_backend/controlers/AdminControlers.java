package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.models.Admin;
import com.groupe.soutoura_backend.services.AdminServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminControlers {


    private final AdminServices adminServices;
    public AdminControlers(AdminServices adminServices){
        this.adminServices = adminServices;
    }

    // CREATE
    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminServices.createAdmin(admin);
    }

    // READ ALL
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminServices.getAllAdmins();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        return adminServices.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin adminDetails) {
        try {
            Admin updatedAdmin = adminServices.updateAdmin(id, adminDetails);
            return ResponseEntity.ok(updatedAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        try {
            adminServices.deleteAdmin(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}