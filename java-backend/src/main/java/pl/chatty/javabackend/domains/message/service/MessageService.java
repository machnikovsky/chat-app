package pl.chatty.javabackend.domains.message.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.message.util.MessageUtils;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageUtils messageUtils;

    public ResponseEntity<String> sendMessage(MessageDTO messageDTO) {
        messageUtils.sendMessage(messageDTO);
        return new ResponseEntity<>("Message has been saved", HttpStatus.OK);
    }

    public ResponseEntity<String> sendMessageSocket(@DestinationVariable String chatID, @RequestBody MessageDTO message){
        return ResponseEntity.ok(messageUtils.sendMessageSocket(chatID, message));
    }
}
