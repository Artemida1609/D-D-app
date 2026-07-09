package mate.academy.jvteamproject.repository.main;

import java.util.Optional;
import mate.academy.jvteamproject.model.main.MagicItem;
import mate.academy.jvteamproject.repository.GeneralRepository;

public interface MagicItemRepository extends GeneralRepository<MagicItem> {
    MagicItem getByOriginalIndex(String originalIndex);

    Optional<MagicItem> findByNameIgnoreCase(String name);

}
