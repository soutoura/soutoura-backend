package com.groupe.soutoura_backend.services;

import com.groupe.soutoura_backend.enume.TypeFichier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DownloadService {

    @Value("${file.upload-dir:${user.home}/Desktop/uploads}")
    private String baseUploadDir;

    public Resource loadFile(String fileName, TypeFichier typeFichier) {
        try {
            Path basePath = Paths.get(baseUploadDir).resolve(getFolderName(typeFichier)).normalize();
            Path filePath = basePath.resolve(fileName).normalize();

            // sécurité path traversal
            if (!filePath.startsWith(basePath)) {
                throw new RuntimeException("Accès non autorisé au fichier : " + fileName);
            }

            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Fichier non trouvé : " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier : " + fileName, e);
        }
    }

    private String getFolderName(TypeFichier typeFichier) {
        return switch (typeFichier) {
            case PHOTO -> "photos";
            case BULLETIN -> "bulletins";
            case RAPPORT -> "rapports";
        };
    }
}