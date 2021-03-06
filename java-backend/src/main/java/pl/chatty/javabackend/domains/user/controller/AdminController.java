package pl.chatty.javabackend.domains.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AdminController {

    @DeleteMapping ("/user/{userID}/ban")
    public ResponseEntity<String> banUser(@PathVariable int userID){
        return new ResponseEntity<>("User has been banned", HttpStatus.OK);
    }
}
