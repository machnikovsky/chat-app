package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class ChatController {

    @GetMapping("message/get")
    public ResponseEntity<String> getMessage(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("message/save")
    public ResponseEntity<String> saveMessage(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("message has been saved", HttpStatus.OK);
    }

    @PostMapping("chat/create")
    public ResponseEntity<String> createChat(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("chat has been created", HttpStatus.OK);
    }

    @PostMapping("group/image")
    public ResponseEntity<String> changeGroupImage(){
        return new ResponseEntity<>("Group image has been changed", HttpStatus.OK);
    }

    @PostMapping("group/name")
    public ResponseEntity<String> changeGroupName(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Group name has been changed", HttpStatus.OK);
    }

    @PostMapping("group/add")
    public ResponseEntity<String> addUserToGroup(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("User was successfully added to group", HttpStatus.OK);
    }

    @PostMapping("group/remove")
    public ResponseEntity<String> removeUserFromGroup(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("User was successfully removed from group", HttpStatus.OK);
    }

    @PostMapping("group/leave")
    public ResponseEntity<String> leaveGroup(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("User successfully left group", HttpStatus.OK);
    }

    @GetMapping("group/users")
    public ResponseEntity<String> getGroupsUsers(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
