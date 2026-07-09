package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import mate.academy.jvteamproject.mapper.main.MagicItemMapper;
import mate.academy.jvteamproject.model.main.MagicItem;
import mate.academy.jvteamproject.repository.main.MagicItemRepository;
import mate.academy.jvteamproject.service.impl.main.MagicItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMagicItem;
import static mate.academy.jvteamproject.helper.TestDataHelper.createMagicItemDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MagicItemServiceTest {
    @InjectMocks
    private MagicItemServiceImpl service;
    @Mock
    private MagicItemRepository repository;
    @Mock
    private MagicItemMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        MagicItem magicItem = createMagicItem("magic-item", "Magic item");
        MagicItemDto expected = createMagicItemDto("magic-item", "Magic item");

        when(repository.getByOriginalIndex(magicItem.getOriginalIndex())).thenReturn(magicItem);
        when(mapper.toDto(magicItem)).thenReturn(expected);

        MagicItemDto actual = service.getByOriginalIndex(magicItem.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        MagicItem first = createMagicItem("magic-item1", "Magic item1");
        MagicItem second = createMagicItem("magic-item2", "Magic item2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<MagicItemDto> magicItems = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, magicItems.getContent().size());
    }

    @Test
    void searchByName_success() {
        MagicItem magicItem = createMagicItem("magic-item", "Magic item");
        MagicItemDto expected = createMagicItemDto("magic-item", "Magic item");

        when(repository.findByNameIgnoreCase(magicItem.getName())).thenReturn(Optional.of(magicItem));
        when(mapper.toDto(magicItem)).thenReturn(expected);

        SearchResult actual = service.searchByName(magicItem.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
