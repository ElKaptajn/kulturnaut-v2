package xenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xenius.model.Band;

public interface BandRepository extends JpaRepository<Band, Long> {
}

