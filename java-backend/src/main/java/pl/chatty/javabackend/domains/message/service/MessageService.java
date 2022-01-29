package pl.chatty.javabackend.domains.message.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.message.util.MessageUtils;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageUtils messageUtils;

    public CompletableFuture<String> sendMessageSocket(@DestinationVariable String chatID, @RequestBody MessageDTO message) throws InterruptedException {
        return messageUtils.sendMessageSocket(chatID, message);
    }
}
