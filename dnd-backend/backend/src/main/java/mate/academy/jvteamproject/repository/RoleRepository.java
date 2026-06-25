package mate.academy.jvteamproject.repository;

import mate.academy.jvteamproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(Role.RoleName name);
}
