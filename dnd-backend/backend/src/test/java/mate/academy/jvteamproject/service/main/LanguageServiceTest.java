package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.language.LanguageDto;
import mate.academy.jvteamproject.mapper.main.LanguageMapper;
import mate.academy.jvteamproject.model.main.Language;
import mate.academy.jvteamproject.repository.main.LanguageRepository;
import mate.academy.jvteamproject.service.impl.main.LanguageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createLanguage;
import static mate.academy.jvteamproject.helper.TestDataHelper.createLanguageDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {
    @InjectMocks
    private LanguageServiceImpl service;
    @Mock
    private LanguageRepository repository;
    @Mock
    private LanguageMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Language language = createLanguage("language", "Language");
        LanguageDto expected = createLanguageDto("language", "Language");

        when(repository.getByOriginalIndex(language.getOriginalIndex())).thenReturn(language);
        when(mapper.toDto(language)).thenReturn(expected);

        LanguageDto actual = service.getByOriginalIndex(language.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Language first = createLanguage("language1", "Language1");
        Language second = createLanguage("language2", "Language2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<LanguageDto> languages = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, languages.getContent().size());
    }

    @Test
    void searchByName_success() {
        Language language = createLanguage("language", "Language");
        LanguageDto expected = createLanguageDto("language", "Language");

        when(repository.findByNameLike(language.getName())).thenReturn(List.of(language));
        when(mapper.toDto(language)).thenReturn(expected);

        SearchResult actual = service.searchByName(language.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
