package mate.academy.jvteamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "health", description = "health controller")
@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    @Operation(summary = "health", description = "return a status 200")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
