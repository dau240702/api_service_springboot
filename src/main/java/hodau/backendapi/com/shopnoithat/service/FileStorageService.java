package hodau.backendapi.com.shopnoithat.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import hodau.backendapi.com.shopnoithat.common.FileStorageException;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.io.IOException;

@Service
public class FileStorageService {
    private static final Dotenv dotenv = Dotenv.configure().load();

    private final String uploadDir;

    public FileStorageService() {
        this.uploadDir = dotenv.get("file.upload-dir");
    }

    public String storeFile(MultipartFile file) throws java.io.IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path targetLocation = Paths.get(uploadDir).resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}