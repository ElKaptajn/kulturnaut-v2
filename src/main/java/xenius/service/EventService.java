package xenius.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xenius.model.Event;
import xenius.repository.EventRepository;

import java.util.*;

@AllArgsConstructor
@Service
public class EventService implements EventServiceInterface{

    private EventRepository eventRepository;

    @Override
    public Set<Event> findAll() {
        Set<Event> set = new TreeSet<>(eventRepository.findAll());
        return set;
    }

    @Override
    public Event save(Event object) {
        return eventRepository.save(object);
    }

    @Override
    public void delete(Event object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Event> findById(Long aLong) {
        return eventRepository.findById(aLong);
    }
}
