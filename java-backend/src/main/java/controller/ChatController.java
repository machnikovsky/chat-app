package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class ChatController {

    @GetMapping("getMessages")
    public Map<String, String> getPortionOfMessages(){
        // function to get portion of message data
        return null;
    }

    @PostMapping("saveMessages")
    public String saveMessages(@RequestBody Map<String, String> json){
        // json will contain many message objects
        // Message objects will contain user name, message content and date
        return null;
    }

    // group name, group photo (optionally), mail of user who want to create group,
    // user emails to indicate which of them will join group.
    @PostMapping("chat/create")
    public ResponseEntity<String> createGroupChat(@RequestBody Map<String, String> json){
        // function to extract data and save to db
        return new ResponseEntity<>("Group successful created", HttpStatus.OK);
    }

    @PostMapping("chat/changeImage")
    public String changeGroupImage(/*no idea*/){
        // no idea
        return null;
    }

    // input data must contain new name and something to indicate which
    // group name should be changed
    // User should have administrator privileges
    @PostMapping("group/changeName")
    public ResponseEntity<String> changeName(@RequestBody Map<String, String> json){
        //function
        return new ResponseEntity<>("Group name successful changed", HttpStatus.OK);
    }

    // input data must contain user detail and something to indicate to
    // which group user should be joined
    // User should have administrator privileges
    @PostMapping("group/addUser")
    public ResponseEntity<String> addUserToGroup(@RequestBody Map<String, String> json){
        // function
        return new ResponseEntity<>("User successfully added to group", HttpStatus.OK);
    }

    // input data must contain user detail and something to indicate to
    // which group user should be removed
    // User should have administrator privileges
    // if user want to leave a group, user to remove and removing user should be the same
    @PostMapping("group/remove")
    public ResponseEntity<String> removeUserFromGroup(@RequestBody Map<String, String> json){
        // function
        return new ResponseEntity<>("User successfully removed from group", HttpStatus.OK);
    }

    // User should have administrator privileges
    @PostMapping("group/nickname")
    public ResponseEntity<String> changeUserNickname(@RequestBody Map<String, String> json){
        // function
        return new ResponseEntity<>("User's nickname successfully changed", HttpStatus.OK);
    }

    @GetMapping("group/users")
    public Map<String,String> getUsersOfGroup(){
        // function which will return user first name, second name and profile image
        return null;
    }
}
