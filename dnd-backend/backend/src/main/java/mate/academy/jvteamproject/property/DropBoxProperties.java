package mate.academy.jvteamproject.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "dropbox")
public class DropBoxProperties {
    private String appKey;
    private String appSecret;
    private String refreshToken;
}
