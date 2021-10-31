package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AdminController {

    @DeleteMapping ("/user/{userID}/ban")
    public ResponseEntity<String> banUser(@PathVariable int userID){
        return new ResponseEntity<>("User has been banned", HttpStatus.OK);
    }
}
