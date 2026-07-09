package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import mate.academy.jvteamproject.mapper.main.MagicItemMapper;
import mate.academy.jvteamproject.repository.main.MagicItemRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.MagicItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MagicItemServiceImpl implements MagicItemService, SearchableService {
    private final MagicItemRepository magicItemRepository;
    private final MagicItemMapper magicItemMapper;

    @Override
    public MagicItemDto getByOriginalIndex(String index) {
        return magicItemMapper.toDto(magicItemRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<MagicItemDto> getAll(Pageable pageable) {
        return magicItemRepository.findAll(pageable)
                .map(magicItemMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return magicItemRepository.findByNameIgnoreCase(name)
                .map(e -> new SearchResult(
                        "magic-items",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        magicItemMapper.toDto(e)
                ));
    }
}
