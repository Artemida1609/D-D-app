package mate.academy.jvteamproject.service.main;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.spell.SpellDto;
import mate.academy.jvteamproject.mapper.main.SpellMapper;
import mate.academy.jvteamproject.model.main.Spell;
import mate.academy.jvteamproject.repository.main.SpellRepository;
import mate.academy.jvteamproject.service.impl.main.SpellServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSpell;
import static mate.academy.jvteamproject.helper.TestDataHelper.createSpellDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpellServiceTest {
    @InjectMocks
    private SpellServiceImpl service;
    @Mock
    private SpellRepository repository;
    @Mock
    private SpellMapper mapper;

    @Test
    void getByOriginalIndex_success() {
        Spell spell = createSpell("spell", "Spell");
        SpellDto expected = createSpellDto("spell", "Spell");

        when(repository.getByOriginalIndex(spell.getOriginalIndex())).thenReturn(spell);
        when(mapper.toDto(spell)).thenReturn(expected);

        SpellDto actual = service.getByOriginalIndex(spell.getOriginalIndex());

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAll_success() {
        Spell first = createSpell("spell1", "Spell1");
        Spell second = createSpell("spell2", "Spell2");
        int expectedSize = 2;

        when(repository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Arrays.asList(first, second)));
        Page<SpellDto> spells = service.getAll(PageRequest.of(0, 10));

        assertEquals(expectedSize, spells.getContent().size());
    }

    @Test
    void searchByName_success() {
        Spell spell = createSpell("spell", "Spell");
        SpellDto expected = createSpellDto("spell", "Spell");

        when(repository.findByNameIgnoreCase(spell.getName())).thenReturn(Optional.of(spell));
        when(mapper.toDto(spell)).thenReturn(expected);

        SearchResult actual = service.searchByName(spell.getName()).orElseThrow();

        assertEquals(expected.getOriginalIndex(), actual.getOriginalIndex());
        assertEquals(expected.getName(), actual.getName());
    }
}
