package mate.academy.jvteamproject.repository;

import mate.academy.jvteamproject.model.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileResourceRepository extends JpaRepository<FileResource, Long> {
    void deleteByEntityId(Long entityId);
}
