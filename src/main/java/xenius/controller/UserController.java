package xenius.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xenius.model.User;
import xenius.service.UserServiceInterface;


@RestController
public class UserController {

    private UserServiceInterface userServiceInterface;

    public UserController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser (@RequestBody User user){
        String msg = "";
        if(userServiceInterface.save(user) != null){
           msg = "User: " + user.getName() +" have been created";
        }else{
            msg = "Error! Server could not create user: " + user.getName();
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
