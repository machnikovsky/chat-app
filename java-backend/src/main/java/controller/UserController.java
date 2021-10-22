package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @PostMapping("user/login")
    public ResponseEntity<String> userLogin(@RequestBody Map<String, String> json){
       // here: function to check user mail and password (send token)
       // user.email and user.password
        return new ResponseEntity<>("Account successful logged", HttpStatus.OK);
    }

    @PostMapping("user/register")
    public ResponseEntity<String> userRegister(/*@RequestBody User user*/){
        // Add user credentials to db
        return new ResponseEntity<>("Account successful registered", HttpStatus.OK);
    }

    @PostMapping("user/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> json) {
        // Function to extract new password and user email and update in db
        return new ResponseEntity<>("Password successful updated", HttpStatus.OK);
    }

    @PostMapping("user/addFriend")
    public ResponseEntity<String> addUserToFriends(@RequestBody Map<String, String> json){
        // Function to extract user email and other friend email
        return new ResponseEntity<>("Successful added to friends", HttpStatus.OK);
    }

    @PostMapping("user/removeFriend")
    public ResponseEntity<String> removeUserFromFriends(@RequestBody Map<String, String> json){
        // Function to extract user email and other friend email
        return new ResponseEntity<>("Successful added to friends", HttpStatus.OK);
    }

    // Global user nickname change
    @PostMapping("user/nickaname")
    public ResponseEntity<String> changeUserNickname(@RequestBody Map<String, String> json){
        // Function to extract user email and other friend email
        return new ResponseEntity<>("User's nickname successfully changed", HttpStatus.OK);
    }

    // Global user nickname change
    @PostMapping("user/username")
    public ResponseEntity<String> changeUsername(@RequestBody Map<String, String> json){
        // Function to extract user email and other friend email
        return new ResponseEntity<>("User's username successfully changed", HttpStatus.OK);
    }
}
