package mate.academy.jvteamproject.repository;

import mate.academy.jvteamproject.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    void deleteByUserId(Long userId);

    void deleteByFileName(String fileName);
}
