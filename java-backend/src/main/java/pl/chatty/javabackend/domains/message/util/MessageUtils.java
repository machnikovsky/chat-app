package pl.chatty.javabackend.domains.message.util;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MessageUtils {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageRepository messageRepository;
    private final ChatUtils chatUtils;
    private final UserUtils userUtils;


    public MessageEntity createNewMessage(String content, UserEntity author, List<UserEntity> receivers) {
        return new MessageEntity(
                content,
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

    public void sendMessage(MessageDTO messageDTO) {

        final String content = messageDTO.getMessageContent();
        final UserEntity messageAuthor = userUtils.getUserByUsername(messageDTO.getMessageAuthorUsername())
                .orElseThrow(() -> new UserEntityNotFoundException(messageDTO.getMessageAuthorUsername()));
        final List<UserEntity> messageReceivers = messageDTO.getMessageReceiversUsernames()
                .stream()
                .map(x -> userUtils.getUserById(x).get())
                .collect(Collectors.toList());


        final ChatEntity chat = chatUtils.getChatByChatId(messageDTO.getChatId())
                .orElseThrow(() -> new UserEntityNotFoundException(messageDTO.getMessageReceiversUsernames().toString())); // TODO: Create chat exception

        final MessageEntity message = saveNewMessageInDatabase(content, messageAuthor, messageReceivers);
        chatUtils.saveNewMessageToChatInDatabase(chat, message);
    }

    public MessageEntity saveNewMessageInDatabase(String content, UserEntity author, List<UserEntity> receivers) {
        MessageEntity message = createNewMessage(content, author, receivers);
        return saveMessageInDatabase(message);
    }

    public void saveNewMessageInDatabase(MessageDTO message) {
        MessageEntity messageEntity = saveMessageInDatabase(new MessageEntity(
                message.getMessageContent(),
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

    public String sendMessageSocket(@DestinationVariable String chatID, @RequestBody MessageDTO message){
        System.out.println("Sending message \"" + message.getMessageContent() + "\" to \"" + chatID + "\"");
        simpMessagingTemplate.convertAndSend("/topic/message/" + chatID, message);
        saveNewMessageInDatabase(message);
        return "Message send";
    }
}
