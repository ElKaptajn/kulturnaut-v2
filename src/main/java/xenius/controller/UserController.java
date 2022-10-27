package xenius.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xenius.model.User;
import xenius.model.Venue;
import xenius.service.UserServiceInterface;
import xenius.service.VenueServiceInterface;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RestController
public class UserController {

    private UserServiceInterface userServiceInterface;
    private VenueServiceInterface venueServiceInterface;


    @PostMapping("/createUser")
    public ResponseEntity<String> createUser (@RequestBody User user){
        String msg = "";
        if(userServiceInterface.save(user) != null){
           msg = "User: " + user.getUsername() +" have been created";
        }else{
            msg = "Error! Server could not create user: " + user.getUsername();
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Set<User>> getAllUsers(){
        return new ResponseEntity<>(userServiceInterface.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createLike")
    public ResponseEntity<String> createLike(@RequestParam Long userId, @RequestParam Long venueId){
        Optional<User> user_ = userServiceInterface.findById(userId);
        Optional<Venue> venue_ = venueServiceInterface.findById(venueId);
        if(user_.isPresent() && venue_.isPresent()){
            user_.get().getVenuesLiked().add(venue_.get());
            userServiceInterface.save(user_.get());
            return new ResponseEntity<>("Gemt", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Fejl", HttpStatus.BAD_REQUEST);
        }
    }
}
