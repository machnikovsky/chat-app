package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class ChatController {

    @GetMapping("/message/{messageID}")
    public ResponseEntity<String> getMessage(@PathVariable long messageID){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/message/{messageID}")
    public ResponseEntity<String> sendMessage(@PathVariable long messageID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("message has been saved", HttpStatus.OK);
    }

    @PostMapping("/chat")
    public ResponseEntity<String> createChat(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("chat has been created", HttpStatus.OK);
    }

    @PostMapping("/group/{groupID}/image")
    public ResponseEntity<String> changeGroupImage(@PathVariable int groupID){
        return new ResponseEntity<>("Group image has been changed", HttpStatus.OK);
    }

    @PostMapping("/group/{groupID}/name")
    public ResponseEntity<String> changeGroupName(@PathVariable int groupID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("Group name has been changed", HttpStatus.OK);
    }

    @PostMapping("/group/{groupID}/user/{userID}")
    public ResponseEntity<String> addUserToGroup(@PathVariable int groupID,
                                                 @PathVariable int userID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("User was successfully added to group", HttpStatus.OK);
    }

    @DeleteMapping("/group/{groupID}/user/{userID}")
    public ResponseEntity<String> removeUserFromGroup(@PathVariable int groupID,
                                                      @PathVariable int userID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("User was successfully removed from group", HttpStatus.OK);
    }

    @PostMapping("/group/{groupID}/leave/user/{userID}")
    public ResponseEntity<String> leaveGroup(@PathVariable int groupID,
                                             @PathVariable int userID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("User successfully left group", HttpStatus.OK);
    }

    @GetMapping("/group/{groupID}")
    public ResponseEntity<String> getGroupsUsers(@PathVariable int groupID){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
