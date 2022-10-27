package xenius.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xenius.model.Venue;
import xenius.repository.VenueRepository;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class VenueService implements VenueServiceInterface{

    private VenueRepository venueRepository;

    @Override
    public Set<Venue> findAll() {
        return null;
    }

    @Override
    public Venue save(Venue object) {
        return null;
    }

    @Override
    public void delete(Venue object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Venue> findById(Long aLong) {
        return venueRepository.findById(aLong);
    }
}
