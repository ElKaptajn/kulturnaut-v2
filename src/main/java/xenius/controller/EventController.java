package xenius.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xenius.model.Band;
import xenius.model.Event;
import xenius.service.BandServiceInterface;
import xenius.service.EventServiceInterface;

import java.util.Optional;
import java.util.Set;


@RestController
public class EventController {

    private EventServiceInterface eventServiceInterface;
    private BandServiceInterface bandServiceInterface;

    public EventController(EventServiceInterface eventServiceInterface, BandServiceInterface bandServiceInterface) {
        this.eventServiceInterface = eventServiceInterface;
        this.bandServiceInterface = bandServiceInterface;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<String> createEvent (@RequestParam Long bandId, @RequestBody Event event) {
        //1. Hent band
        Optional<Band> band_ = bandServiceInterface.findById(bandId);

        if (band_.isPresent()) {
            Band band = band_.get();
            event.setBand(band);
            eventServiceInterface.save(event);

            return new ResponseEntity<>("Event created: " + event.getVenue(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Event NOT created: " + event.getVenue(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEvents")
    public ResponseEntity<Set<Event>> getEvents(){
        return new ResponseEntity<>(eventServiceInterface.findAll(), HttpStatus.OK);
    }
}
