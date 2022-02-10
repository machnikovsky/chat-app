package pl.chatty.javabackend.domains.message.util;

import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import pl.chatty.javabackend.domains.chat.model.entity.ChatEntity;
import pl.chatty.javabackend.domains.chat.util.ChatUtils;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.message.model.entity.MessageEntity;
import pl.chatty.javabackend.domains.message.model.entity.MessageType;
import pl.chatty.javabackend.domains.message.repository.MessageRepository;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.util.UserUtils;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Slf4j
public class MessageUtils {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageRepository messageRepository;
    private final ChatUtils chatUtils;
    private final UserUtils userUtils;


    public MessageEntity createNewMessage(String content,String imageContent, UserEntity author, List<UserEntity> receivers) {
        return new MessageEntity(
                content,
                imageContent,
                LocalDate.now(),
                MessageType.TEXT,
                author.getUserId(),
                new ArrayList<>(receivers.stream().map(UserEntity::getUserId).collect(Collectors.toList()))
        );
    }

    public Optional<MessageEntity> getMessageByMessageId(String messageId) {
        return messageRepository.findById(messageId);
    }

    public MessageEntity saveMessageInDatabase(MessageEntity message) {
        return messageRepository.save(message);
    }


    public MessageEntity saveNewMessageInDatabase(String content,String imageContent, UserEntity author, List<UserEntity> receivers) {
        MessageEntity message = createNewMessage(content,imageContent, author, receivers);
        return saveMessageInDatabase(message);
    }

    public void saveNewMessageInDatabase(MessageDTO message) {
        MessageEntity messageEntity = saveMessageInDatabase(new MessageEntity(
                message.getMessageContent(),
                message.getImageContent(),
                LocalDate.now(),
                MessageType.TEXT,
                message.getMessageAuthorUsername(),
                message.getMessageReceiversUsernames()
        ));
        chatUtils.saveNewMessageToChatInDatabase(
                chatUtils.getChatByChatId(message.getChatId()).orElseThrow(() -> new RuntimeException("")),
                messageEntity
        );
    }

    @Async("asyncTaskExecutor")
    public CompletableFuture<String> sendMessageSocket(@DestinationVariable String chatID, @RequestBody MessageDTO message) throws InterruptedException {
        log.info("Starting to send message via WebSocket using thread: {}", Thread.currentThread());
        Thread.sleep(200);
        simpMessagingTemplate.convertAndSend("/topic/message/" + chatID, message);
        saveNewMessageInDatabase(message);
        log.info("Finished sending message via WebSocket using thread: {}", Thread.currentThread());
        return CompletableFuture.completedFuture("Message send");
    }
}
