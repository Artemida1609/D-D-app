package mate.academy.jvteamproject.service;

import java.util.Optional;
import mate.academy.jvteamproject.dto.SearchResult;

public interface SearchableService {
    Optional<SearchResult> searchByName(String name);
}
