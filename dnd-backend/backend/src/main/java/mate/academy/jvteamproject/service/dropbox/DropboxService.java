package mate.academy.jvteamproject.service.dropbox;

import org.springframework.web.multipart.MultipartFile;

public interface DropboxService {
    String upload(MultipartFile file, String filename);

    byte[] downloadFile(String url);

    void deleteFile(String fileId);
}
