package xenius.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import xenius.model.Band;
import xenius.repository.BandRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class BandService implements BandServiceInterface{

    private BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public Set<Band> findAll() {
        return null;
    }

    @Override
    public Band save(Band object) {
        return bandRepository.save(object);
    }

    @Override
    public void delete(Band object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Band> findById(Long aLong) {
        return bandRepository.findById(aLong);
    }
}
