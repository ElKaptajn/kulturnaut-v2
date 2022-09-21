package xenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xenius.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
