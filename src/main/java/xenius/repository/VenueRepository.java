package xenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xenius.model.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
