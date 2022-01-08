package pl.chatty.javabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.chatty.javabackend.WebSocketsTest.MessageModel;
import pl.chatty.javabackend.WebSocketsTest.UserStorage;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    MessageController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat/{chatID}")
    public String sendMessage(@DestinationVariable String chatID, @RequestBody MessageModel message){
        System.out.println("Sending message \"" + message + "\" to \"" + chatID + "\"");
//        if(UserStorage.users.contains(userID))
            simpMessagingTemplate.convertAndSend("/topic/message/" + chatID, message);
        return "Message send";
    }
}
