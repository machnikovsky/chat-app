package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful logged", HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Account successful registered", HttpStatus.OK);
    }

    @PutMapping("{userID}/password")
    public ResponseEntity<String> updatePassword(@PathVariable int userID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("Password successful updated", HttpStatus.OK);
    }

    @PostMapping("{userID}/friend/{friendID}")
    public ResponseEntity<String> addUserToFriends(@PathVariable int userID,
                                                   @PathVariable int friendID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful added to friends", HttpStatus.OK);
    }

    @DeleteMapping ("{userID}/friend/{friendID}")
    public ResponseEntity<String> removeUserFromFriends(@PathVariable int userID,
                                                        @PathVariable int friendID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful removed friend", HttpStatus.OK);
    }

    @PostMapping("{userID}/friend/{friendID}/nickname")
    public ResponseEntity<String> changeFriendsUsername(@PathVariable int userID,
                                                        @PathVariable int friendID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("User's nickname successfully changed", HttpStatus.OK);
    }
}
