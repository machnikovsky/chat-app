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
    public ResponseEntity<String> login(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful logged", HttpStatus.OK);
    }

    @PostMapping("user/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Account successful registered", HttpStatus.OK);
    }

    @PostMapping("user/password/update")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> json) {
        return new ResponseEntity<>("Password successful updated", HttpStatus.OK);
    }

    @PostMapping("user/friend/add")
    public ResponseEntity<String> addUserToFriends(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful added to friends", HttpStatus.OK);
    }

    @PostMapping("user/friend/remove")
    public ResponseEntity<String> removeUserFromFriends(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful removed friend", HttpStatus.OK);
    }

    @PostMapping("user/friend/nickname")
    public ResponseEntity<String> changeFriendsUsername(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("User's nickname successfully changed", HttpStatus.OK);
    }
}
