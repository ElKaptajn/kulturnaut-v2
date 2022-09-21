package xenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xenius.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
