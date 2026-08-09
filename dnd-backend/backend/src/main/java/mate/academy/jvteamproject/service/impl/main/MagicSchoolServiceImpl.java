package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import mate.academy.jvteamproject.mapper.main.MagicSchoolMapper;
import mate.academy.jvteamproject.repository.main.MagicSchoolRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.main.MagicSchoolService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MagicSchoolServiceImpl implements MagicSchoolService, SearchableService {
    private final MagicSchoolRepository magicSchoolRepository;
    private final MagicSchoolMapper magicSchoolMapper;

    @Override
    public MagicSchoolDto getByOriginalIndex(String index) {
        return magicSchoolMapper.toDto(magicSchoolRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<MagicSchoolDto> getAll(Pageable pageable) {
        return magicSchoolRepository.findAll(pageable)
                .map(magicSchoolMapper::toDto);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return magicSchoolRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "magic-schools",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        magicSchoolMapper.toDto(e)
                ));
    }
}
