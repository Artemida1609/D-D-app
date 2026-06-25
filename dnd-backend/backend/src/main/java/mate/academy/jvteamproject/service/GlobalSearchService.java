package mate.academy.jvteamproject.service;

import java.util.List;
import java.util.Optional;
import mate.academy.jvteamproject.dto.api.SearchResult;
import org.springframework.stereotype.Service;

@Service
public class GlobalSearchService {

    private final List<SearchableService> services;

    public GlobalSearchService(List<SearchableService> services) {
        this.services = services;
    }

    public List<SearchResult> search(String name) {
        return services.stream()
                .map(s -> s.searchByName(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
