package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.feature.FeatureDto;
import mate.academy.jvteamproject.mapper.main.FeatureMapper;
import mate.academy.jvteamproject.model.main.Feature;
import mate.academy.jvteamproject.repository.main.FeatureRepository;
import mate.academy.jvteamproject.service.impl.main.FeatureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createFeature;
import static mate.academy.jvteamproject.helper.TestDataHelper.createFeatureDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeatureServiceTest {
    @InjectMocks
    private FeatureServiceImpl service;
    @Mock
    private FeatureRepository repository;
    @Mock
    private FeatureMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Feature feature = createFeature("feature", "Feature");
        FeatureDto expected = createFeatureDto("feature", "Feature");

        when(repository.getByOriginalIndex(feature.getOriginalIndex())).thenReturn(feature);
        when(mapper.toDto(feature)).thenReturn(expected);

        FeatureDto actual = service.getByOriginalIndex(feature.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Feature first = createFeature("feature1", "Feature1");
        Feature second = createFeature("feature2", "Feature2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<FeatureDto> features = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, features.getContent().size());
    }

    @Test
    void searchByName_success() {
        Feature feature = createFeature("feature", "Feature");
        FeatureDto expected = createFeatureDto("feature", "Feature");

        when(repository.findByNameLike(feature.getName())).thenReturn(List.of(feature));
        when(mapper.toDto(feature)).thenReturn(expected);

        SearchResult actual = service.searchByName(feature.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
