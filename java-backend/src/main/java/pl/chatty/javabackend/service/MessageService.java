package pl.chatty.javabackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.model.dao.MessageEntity;
import pl.chatty.javabackend.repository.MessageRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageEntity saveMessageInDatabase(MessageEntity message) {
        return messageRepository.save(message);
    }

    public Optional<MessageEntity> getMessageByMessageId(String messageId) {
        return messageRepository.findById(messageId);
    }
}
