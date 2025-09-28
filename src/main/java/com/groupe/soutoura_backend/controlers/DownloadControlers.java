package com.groupe.soutoura_backend.controlers;



import com.groupe.soutoura_backend.enume.TypeFichier;
import com.groupe.soutoura_backend.services.DownloadService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/files")
public class DownloadControlers {

    private final DownloadService downloadService;

    public DownloadControlers(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @GetMapping("/v1/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam("fileName") String fileName,
            @RequestParam("typeFichier") TypeFichier typeFichier) {

        Resource resource = downloadService.loadFile(fileName, typeFichier);

        String contentType;
        try {
            Path path = resource.getFile().toPath();
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
