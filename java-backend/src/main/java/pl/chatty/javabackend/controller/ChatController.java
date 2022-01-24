package pl.chatty.javabackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chatty.javabackend.model.dto.request.ChatParticipantsDTO;
import pl.chatty.javabackend.model.dto.request.CreateChatRequestDTO;
import pl.chatty.javabackend.model.dto.request.MessageDTO;
import pl.chatty.javabackend.model.dto.response.ChatDTO;
import pl.chatty.javabackend.service.ChatService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/message/{messageID}")
    public ResponseEntity<String> getMessage(@PathVariable long messageID) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<MessageDTO>> getAllChatMessages(@PathVariable String chatId) {
        return ResponseEntity.ok(chatService.getAllChatMessages(chatId));
    }

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO message) {
        return chatService.sendMessage(message);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createChat(@RequestBody  CreateChatRequestDTO createChatRequestDTO) {
        return ResponseEntity.ok(chatService.createNewChat(createChatRequestDTO).getChatId());
    }

    @GetMapping("/find")
    public ResponseEntity<String> getChatIdIfPresent(@RequestBody ChatParticipantsDTO chatParticipantsDTO) {
        return ResponseEntity.ok(chatService.getChatByChatParticipants(chatParticipantsDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatDTO>> getAllUserChats() {
        return ResponseEntity.ok(chatService.getAllUserChats());
    }






    @PostMapping("/group/{groupID}/image")
    public ResponseEntity<String> changeGroupImage(@PathVariable int groupID) {
        return new ResponseEntity<>("Group image has been changed", HttpStatus.OK);
    }

    @PostMapping("/group/{groupID}/name")
    public ResponseEntity<String> changeGroupName(@PathVariable int groupID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("Group name has been changed", HttpStatus.OK);
    }

    @PostMapping("/group/{groupID}/user/{userID}")
    public ResponseEntity<String> addUserToGroup(@PathVariable int groupID,
                                                 @PathVariable int userID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("User was successfully added to group", HttpStatus.OK);
    }

    @DeleteMapping("/group/{groupID}/user/{userID}")
    public ResponseEntity<String> removeUserFromGroup(@PathVariable int groupID,
                                                      @PathVariable int userID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("User was successfully removed from group", HttpStatus.OK);
    }

    @PostMapping("/group/{groupID}/leave/user/{userID}")
    public ResponseEntity<String> leaveGroup(@PathVariable int groupID,
                                             @PathVariable int userID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("User successfully left group", HttpStatus.OK);
    }

    @GetMapping("/group/{groupID}")
    public ResponseEntity<String> getGroupsUsers(@PathVariable int groupID) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
