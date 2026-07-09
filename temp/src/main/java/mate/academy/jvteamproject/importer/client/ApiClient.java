package mate.academy.jvteamproject.importer.client;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ApiClient {
    private static final String BASE_URL = "https://www.dnd5eapi.co";

    private final RestTemplate restTemplate;

    public <T> T get(String endpoint, Class<T> clazz) {
        String url = endpoint.startsWith("http")
                ? endpoint
                : BASE_URL + endpoint;
        int attempts = 0;

        while (attempts < 5) {
            try {

                return restTemplate.getForObject(url, clazz);

            } catch (Exception e) {
                attempts++;

                long delay = 500L * attempts;
                System.err.println("Retry " + attempts + " for " + url + " after " + delay + "ms");

                try {
                    Thread.sleep(delay + new Random().nextInt(200));
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        throw new RuntimeException("Failed to GET " + url);
    }
}
