package xenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xenius.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
