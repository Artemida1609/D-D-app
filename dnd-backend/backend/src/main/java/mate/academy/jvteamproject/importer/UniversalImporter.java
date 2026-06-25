package mate.academy.jvteamproject.importer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.ApiListResponse;
import mate.academy.jvteamproject.dto.api.ApiReferenceDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.importer.client.ApiClient;
import mate.academy.jvteamproject.mapper.main.LevelMapper;
import mate.academy.jvteamproject.model.main.Level;
import mate.academy.jvteamproject.repository.main.LevelRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversalImporter {

    private final ApiClient apiClient;
    private final UniversalReferenceFixer referenceFixer;
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    public <D, E> void importAll(ImportDefinition<D, E> def) {
        System.out.println("Importing: " + def.endpoint());

        ApiListResponse list = apiClient.get(def.endpoint(), ApiListResponse.class);

        for (ApiReferenceDto ref : list.getResults()) {
            try {
                Thread.sleep(100 + new Random().nextInt(150));

                D dto = apiClient.get(ref.getUrl(), def.dtoClass());

                String originalIndex = extractIndex(dto);
                if (def.repository().existsByOriginalIndex(originalIndex)) {
                    continue;
                }

                referenceFixer.fix(dto);
                E entity = def.mapper().toEntity(dto);
                setOriginalIndex(entity, originalIndex);

                def.repository().save(entity);

            } catch (Exception e) {
                System.err.println("Error importing " + ref.getUrl() + ": " + e.getMessage());
            }
        }
    }

    public void importLevels(String baseUrl, String index, boolean isSubclass) {

        List<Map<String, Object>> refs =
                apiClient.get(baseUrl + "/" + index + "/levels", List.class);

        for (Map<String, Object> ref : refs) {
            String url = (String) ref.get("url");

            LevelDto dto = apiClient.get(url, LevelDto.class);
            referenceFixer.fix(dto);
            Level entity = levelMapper.toEntity(dto);

            if (levelRepository.existsByOriginalIndex(entity.getOriginalIndex())) {
                continue;
            }

            if (isSubclass) {
                entity.setOriginalIndex(index);
                entity.setUrl("/api/subclasses/"
                        + index + "/level/" + dto.getLevel());
            } else {
                entity.setOriginalIndex(index);
                entity.setUrl("/api/classes/"
                        + index + "/level/" + dto.getLevel());
            }

            levelRepository.save(entity);
        }
    }

    private <D> String extractIndex(D dto) {
        try {
            Field f = dto.getClass().getDeclaredField("originalIndex");
            f.setAccessible(true);
            return (String) f.get(dto);
        } catch (Exception e) {
            throw new RuntimeException("DTO has no originalIndex field");
        }
    }

    private <E> void setOriginalIndex(E entity, String index) {
        try {
            Field f = entity.getClass().getDeclaredField("originalIndex");
            f.setAccessible(true);
            f.set(entity, index);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
