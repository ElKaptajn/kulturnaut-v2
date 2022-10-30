package xenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xenius.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String name);
}
