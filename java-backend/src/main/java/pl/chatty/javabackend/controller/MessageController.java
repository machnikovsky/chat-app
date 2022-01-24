package pl.chatty.javabackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.chatty.javabackend.model.dto.request.MessageDTO;
import pl.chatty.javabackend.service.ChatService;
import pl.chatty.javabackend.service.MessageService;


@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final ChatService chatService;


    @MessageMapping("/chat/{chatID}")
    public String sendMessage(@DestinationVariable String chatID, @RequestBody MessageDTO message){
        System.out.println("Sending message \"" + message.getMessageContent() + "\" to \"" + chatID + "\"");
        simpMessagingTemplate.convertAndSend("/topic/message/" + chatID, message);
        chatService.saveNewMessageInDatabase(message);
        return "Message send";
    }
}
