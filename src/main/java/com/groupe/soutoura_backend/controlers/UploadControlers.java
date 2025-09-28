package com.groupe.soutoura_backend.controlers;

import com.groupe.soutoura_backend.enume.TypeFichier;
import com.groupe.soutoura_backend.services.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class UploadControlers {

    private final UploadService uploadService;

    public UploadControlers(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam("typeFichier") TypeFichier typeFichier) {

        String savedFileName = uploadService.uploadFile(file,fileName, typeFichier);

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/v1/download")
                .queryParam("fileName", savedFileName)
                .queryParam("typeFichier", typeFichier)
                .toUriString();

        Map<String, String> response = new HashMap<>();
        response.put("fileName", savedFileName);
        response.put("downloadUrl", downloadUrl);

        return ResponseEntity.ok(response);
    }
}

