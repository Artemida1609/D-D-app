package mate.academy.jvteamproject.repository;

import java.util.Optional;
import mate.academy.jvteamproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User getUserByEmail(String email);
}
