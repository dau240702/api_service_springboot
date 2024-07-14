package hodau.backendapi.com.shopnoithat.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.io.IOException;

@Service
public class FileStorageService {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private final String uploadDir;

    public FileStorageService() {
        this.uploadDir = dotenv.get("file.upload-dir");
    }

    public String storeFile(MultipartFile file) throws IOException, java.io.IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        Path targetLocation = Paths.get(uploadDir).resolve(uniqueFileName);

        if (!Files.exists(Paths.get(uploadDir))) {
            Files.createDirectories(Paths.get(uploadDir));
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + uniqueFileName;
        } catch (IOException ex) {
            throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}