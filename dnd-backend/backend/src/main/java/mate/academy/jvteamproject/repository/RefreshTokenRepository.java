package mate.academy.jvteamproject.repository;

import java.util.Optional;
import mate.academy.jvteamproject.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);
}
