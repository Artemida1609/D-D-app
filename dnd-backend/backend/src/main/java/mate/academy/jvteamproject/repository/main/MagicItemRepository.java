package mate.academy.jvteamproject.repository.main;

import java.util.List;
import mate.academy.jvteamproject.model.main.MagicItem;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.jpa.repository.Query;

public interface MagicItemRepository extends GeneralRepository<MagicItem> {
    MagicItem getByOriginalIndex(String originalIndex);

    @Query("""
            SELECT mItem FROM MagicItem mItem
            WHERE LOWER(mItem.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<MagicItem> findByNameLike(String name);
}
