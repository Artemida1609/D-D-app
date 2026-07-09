package mate.academy.jvteamproject.repository;

import java.util.List;
import mate.academy.jvteamproject.model.SavedElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedElementRepository extends JpaRepository<SavedElement, Long> {
    List<SavedElement> findByUserId(Long userId);
}
