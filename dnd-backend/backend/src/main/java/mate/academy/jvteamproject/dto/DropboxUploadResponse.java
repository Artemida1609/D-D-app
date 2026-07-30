package mate.academy.jvteamproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DropboxUploadResponse {
    private String fileId;
    private String fileName;
    private String imageUrl;
}
