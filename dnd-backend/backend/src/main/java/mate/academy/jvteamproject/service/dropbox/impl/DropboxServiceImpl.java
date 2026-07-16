package mate.academy.jvteamproject.service.dropbox.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import java.io.InputStream;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.exception.DropBoxException;
import mate.academy.jvteamproject.property.DropBoxProperties;
import mate.academy.jvteamproject.service.dropbox.DropboxService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@Getter
@Setter
public class DropboxServiceImpl implements DropboxService {
    private final DbxClientV2 client;

    public DropboxServiceImpl(DropBoxProperties properties) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dnd_app").build();

        DbxCredential credential = new DbxCredential(
                "",
                0L,
                properties.getRefreshToken(),
                properties.getAppKey(),
                properties.getAppSecret()
        );

        this.client = new DbxClientV2(config, credential);
    }

    @Override
    public String upload(MultipartFile file, String filename) {
        try (InputStream in = file.getInputStream()) {
            client.files().uploadBuilder("/" + filename)
                    .uploadAndFinish(in);

            SharedLinkMetadata link = client.sharing()
                    .createSharedLinkWithSettings("/" + filename);

            return link.getUrl().replace("dl=0", "raw=1");
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload to Dropbox", e);
        }
    }

    @Override
    public byte[] downloadFile(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, byte[].class);
    }

    @Override
    public void deleteFile(String fileId) {
        try {
            client.files().deleteV2(fileId);
        } catch (DeleteErrorException e) {
            if (e.errorValue.isPathLookup()
                    && e.errorValue.getPathLookupValue().isNotFound()) {

                return;
            }
            throw new DropBoxException("Failed to delete file from Dropbox: " + fileId, e);
        } catch (DbxException e) {
            throw new DropBoxException("Dropbox delete error", e);
        }
    }
}
