package mate.academy.jvteamproject.repository.main;

import mate.academy.jvteamproject.model.main.Level;
import mate.academy.jvteamproject.repository.GeneralRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LevelRepository extends GeneralRepository<Level> {
    boolean existsByOriginalIndex(String originalIndex);

    @Query("SELECT l FROM Level l WHERE l.originalIndex = :originalIndex AND l.level = :level")
    Level getLevelByOriginalIndex(@Param("originalIndex") String originalIndex,
                   @Param("level") int level);

    @Query("SELECT l FROM Level l WHERE l.originalIndex = :originalIndex")
    Page<Level> getAllLevelsByOriginalIndex(@Param("originalIndex")String originalIndex,
                                            Pageable pageable);
}
