package pl.chatty.javabackend.domains.message.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.message.service.MessageService;

import java.util.concurrent.CompletableFuture;


@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/chat/{chatID}")
    public ResponseEntity<CompletableFuture<String>> sendMessageSocket(@DestinationVariable String chatID, @RequestBody MessageDTO message) throws InterruptedException {
        System.out.println(message.toString());
        return ResponseEntity.ok(messageService.sendMessageSocket(chatID, message));
    }

}
