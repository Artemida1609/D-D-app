package mate.academy.jvteamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.importer.ImporterRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "import", description = "import data from api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/import")
public class ImportRunnerController {
    private final ImporterRunner importerRunner;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/start")
    @Operation(summary = "import", description = "import all data")
    public ResponseEntity<String> importData() {
        importerRunner.run();
        return ResponseEntity.accepted().body("Import started");
    }
}
