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

    /**
     * Récupère le fichier en tant que Resource
     */
    public Resource loadFile(String fileName, TypeFichier typeFichier) {
        try {
            Path filePath = Paths.get(baseUploadDir)
                    .resolve(getFolderName(typeFichier))
                    .resolve(fileName)
                    .normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Fichier non trouvé ou illisible : " + fileName);
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
