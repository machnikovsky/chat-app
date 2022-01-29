package pl.chatty.javabackend.domains.chat.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatParticipantsDTO;
import pl.chatty.javabackend.domains.chat.model.dto.request.CreateChatRequestDTO;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatDTO;
import pl.chatty.javabackend.domains.chat.service.ChatService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<MessageDTO>> getAllChatMessages(@PathVariable String chatId) {
        return chatService.getAllChatMessages(chatId);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createChatAndGetChatId(@RequestBody  CreateChatRequestDTO createChatRequestDTO) {
        return chatService.createChatAndGetChatId(createChatRequestDTO);
    }

    @GetMapping("/find")
    public ResponseEntity<String> getChatIdIfPresent(@RequestBody ChatParticipantsDTO chatParticipantsDTO) {
        return chatService.getChatByChatParticipants(chatParticipantsDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatDTO>> getAllUserChats() {
        return chatService.getAllUserChats();
    }

    @GetMapping("/send/{userId}")
    public ResponseEntity<ChatDTO> getExistingChatOrCreateNew(@PathVariable("userId") String userId) {
        return chatService.getExistingChatOrCreateNew(userId);
    }






    //TODO: Implement these or remove them
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
