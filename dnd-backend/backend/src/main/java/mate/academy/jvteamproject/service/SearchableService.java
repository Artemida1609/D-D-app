package mate.academy.jvteamproject.service;

import java.util.Optional;
import mate.academy.jvteamproject.dto.api.SearchResult;

public interface SearchableService {
    Optional<SearchResult> searchByName(String name);
}
