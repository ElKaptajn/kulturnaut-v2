package xenius.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xenius.model.Band;
import xenius.model.Event;
import xenius.model.Review;
import xenius.model.User;
import xenius.service.EventServiceInterface;
import xenius.service.ReviewServiceInterface;
import xenius.service.UserServiceInterface;

import java.util.Optional;

@RestController
public class ReviewController {

    private ReviewServiceInterface reviewServiceInterface;
    private EventServiceInterface eventServiceInterface;
    private UserServiceInterface userServiceInterface;

    public ReviewController(ReviewServiceInterface reviewServiceInterface, EventServiceInterface eventServiceInterface, UserServiceInterface userServiceInterface) {
        this.reviewServiceInterface = reviewServiceInterface;
        this.eventServiceInterface = eventServiceInterface;
        this.userServiceInterface = userServiceInterface;
    }


    @PostMapping("/createReview")
    public ResponseEntity<String> createReview(@RequestBody Review review, @RequestParam Long eventId, @RequestParam Long userId){

        //Tjek om du kan se alle reviews fra et event!

        Optional<Event> event_ = eventServiceInterface.findById(eventId);
        Optional<User> user_ = userServiceInterface.findById(userId);

        if(user_.isPresent() && event_.isPresent()){
            User user = user_.get();
            Event event = event_.get();

            review.addUser(user);
            review.addEvent(event);
            reviewServiceInterface.save(review);

            event.addReview(review);
            eventServiceInterface.save(event);
            return new ResponseEntity<>("Review created", HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getReviewsByEvent/{eventId}")
    public ResponseEntity<Event> getReviewsByEvent(@PathVariable("eventId") Long eventId){
        return new ResponseEntity<>(eventServiceInterface.findById(eventId).get(), HttpStatus.OK);
    }
}
