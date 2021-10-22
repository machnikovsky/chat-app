package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {

    //  Email address will indicate which account should be ban
    @PostMapping("chatty/banUser")
    public ResponseEntity<String> banUser(@RequestBody String email){
        // call ban user function
        return new ResponseEntity<>("Account successful banned", HttpStatus.OK);
    }
}
