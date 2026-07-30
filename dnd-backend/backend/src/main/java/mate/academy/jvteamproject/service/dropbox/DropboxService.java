package mate.academy.jvteamproject.service.dropbox;

import mate.academy.jvteamproject.dto.DropboxUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DropboxService {
    DropboxUploadResponse upload(MultipartFile file, String filename);

    byte[] downloadFile(String url);

    void deleteFile(String fileId);
}
