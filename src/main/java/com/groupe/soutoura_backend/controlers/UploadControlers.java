package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.enume.TypeFichier;
import com.groupe.soutoura_backend.services.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadControlers {

    private final UploadService uploadService;

    public UploadControlers(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/{type}")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam("typeFichier") TypeFichier typeFichier) {

        String savedFileName = uploadService.uploadFile(file,fileName, typeFichier);

        return ResponseEntity.ok(savedFileName); // Le front reçoit le nom généré
    }
}

