package xenius.security;

import lombok.NoArgsConstructor;
import xenius.security.models.JwtRequestModel;
import xenius.security.models.JwtResponseModel;
import xenius.model.User;
import xenius.service.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class JwtController {
    private JwtUserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private UserServiceInterface userService;

    @PostMapping("/signup")
    public ResponseEntity<JwtResponseModel> signup(@RequestBody JwtRequestModel request){
        System.out.println("signup: username:" + request.getUsername() + " password: " + request.getPassword() );
        User user = new User(request.getUsername(),request.getPassword());
        if(userService.findByName(user.getUsername()).size()==0) {
            if (userService.save(user) != null) {
                return ResponseEntity.ok(new JwtResponseModel("created user: " + user.getUsername() + " pw: " + user.getPassword()));
            } else {
                return ResponseEntity.ok(new JwtResponseModel("error creating user: " + user.getUsername()));
            }
        }else {
            return ResponseEntity.ok(new JwtResponseModel("error: user exists: " + user.getUsername()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception {
        // HttpServletRequest servletRequest is available from Spring, if needed.
        System.out.println(" JwtController createToken Call: 4" + request.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new JwtResponseModel("bad credentials"));
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }


    @PostMapping("/getSecret")
    public ResponseEntity<Map> getSecret() {
        System.out.println("getSecret is called");
        Map<String,String > map = new HashMap<>();
        map.put("message","this is secret from server");
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Map> deleteUser(@RequestBody User user) { // hvis man kommer hertil, er token ok

        List<User> users = userService.findByName(user.getUsername());
        User userToDelete = users.get(0);

        System.out.println(userToDelete);

        userService.delete(userToDelete);

        Map<String,String > map = new HashMap<>();

        map.put("message","User deleted if found: " + user.getUsername());

        return ResponseEntity.ok(map);
    }


    @PutMapping("/updateUser")
    public ResponseEntity<Map> updateUser(@RequestBody User user) { // hvis man kommer hertil, er token ok

        List<User> users = userService.findByName(user.getUsername());
        User userToUpdate = users.get(0);

        userToUpdate.setPassword(user.getPassword());

        userService.save(userToUpdate);

        Map<String,String > map = new HashMap<>();

        map.put("message","User by the name of: " + user.getUsername() + "Has been updated!");

        return ResponseEntity.ok(map);
    }


}
