package mate.academy.jvteamproject.importer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.model.main.Class;
import mate.academy.jvteamproject.model.main.Subclass;
import mate.academy.jvteamproject.repository.main.ClassRepository;
import mate.academy.jvteamproject.repository.main.SubclassRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ImporterRunner {

    private final UniversalImporter importer;
    private final EquipmentCategoryImporter equipmentCategoryImporter;
    private final List<ImportDefinition<?, ?>> definitions;
    private final ClassRepository classRepository;
    private final SubclassRepository subclassRepository;

    public void run() {
        System.out.println("=== FIRST PASS: IMPORTING IN PARALLEL ===");

        ExecutorService executor = Executors.newFixedThreadPool(definitions.size());

        for (ImportDefinition<?, ?> def : definitions) {
            executor.submit(() -> importer.importAll(def));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("=== IMPORTING EQUIPMENT CATEGORIES ===");

        equipmentCategoryImporter.importCategories();

        System.out.println("=== IMPORTING LEVELS ===");
        for (Class c : classRepository.findAll()) {
            importer.importLevels("/api/classes/", c.getOriginalIndex(), false);
        }
        for (Subclass s : subclassRepository.findAll()) {
            importer.importLevels("/api/subclasses/", s.getOriginalIndex(), true);
        }
        System.out.println("=== FIRST PASS COMPLETED ===");
    }
}
