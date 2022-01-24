package pl.chatty.javabackend.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;
import pl.chatty.javabackend.model.MessageType;
import pl.chatty.javabackend.model.dao.ChatEntity;
import pl.chatty.javabackend.model.dao.MessageEntity;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.ChatParticipantsDTO;
import pl.chatty.javabackend.model.dto.request.CreateChatRequestDTO;
import pl.chatty.javabackend.model.dto.request.MessageDTO;
import pl.chatty.javabackend.model.dto.response.ChatDTO;
import pl.chatty.javabackend.model.dto.response.UserDTO;
import pl.chatty.javabackend.repository.ChatRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public ChatEntity createNewChat(CreateChatRequestDTO createChatRequestDTO) {
        return addNewChatToDatabaseFromMessage(
                createChatRequestDTO
                        .getChatParticipantsUsernames()
                        .stream()
                        .map(username -> userService
                                .getUserByUsername(username)
                                .orElseThrow(() -> new UserEntityNotFoundException(username)))
                        .collect(Collectors.toList())
        );
    }

    public ResponseEntity<String> sendMessage(MessageDTO messageDTO) {

        final String content = messageDTO.getMessageContent();
        final UserEntity messageAuthor = userService.getUserByUsername(messageDTO.getMessageAuthorUsername())
                .orElseThrow(() -> new UserEntityNotFoundException(messageDTO.getMessageAuthorUsername()));
        final List<UserEntity> messageReceivers = messageDTO.getMessageReceiversUsernames()
                .stream()
                .map(x -> userService.getUserById(x).get())
                .collect(Collectors.toList());


        final ChatEntity chat = getChatByChatId(messageDTO.getChatId())
                .orElseThrow(() -> new UserEntityNotFoundException(messageDTO.getMessageReceiversUsernames().toString())); // TODO: Create chat exception

        final MessageEntity message = saveNewMessageInDatabase(content, messageAuthor, messageReceivers);
        saveNewMessageToChatInDatabase(chat, message);

        return new ResponseEntity<>("Message has been saved", HttpStatus.OK);
    }

    public String getChatByChatParticipants(ChatParticipantsDTO chatParticipantsDTO) {
        return chatRepository.findByChatParticipants(chatParticipantsDTO.getChatParticipantsIds())
                .map(ChatEntity::getChatId)
                .orElseThrow(() -> new UserEntityNotFoundException("")); // TODO: Create chat exception
    }

    public Optional<ChatEntity> getChatByChatId(String chatId) {
        return chatRepository.findByChatId(chatId);
    }

    public ChatEntity addNewChatToDatabaseFromMessage(List<UserEntity> users) {
        ChatEntity chat = new ChatEntity();
        chat.setMembersIds(new ArrayList<>(users.stream().map(UserEntity::getUserId).collect(Collectors.toList())));
        chat.setCreatedTime(LocalDate.now());
        chat.setMessageIds(new ArrayList<>());
        chat.setName("");
        return chatRepository.save(chat);
    }

    public MessageEntity saveNewMessageInDatabase(String content, UserEntity author, List<UserEntity> receivers) {
        MessageEntity message = createNewMessage(content, author, receivers);
        return messageService.saveMessageInDatabase(message);
    }

    public void saveNewMessageInDatabase(MessageDTO message) {
        MessageEntity messageEntity = messageService.saveMessageInDatabase(new MessageEntity(
                message.getMessageContent(),
                LocalDate.now(),
                MessageType.TEXT,
                message.getMessageAuthorUsername(),
                message.getMessageReceiversUsernames()
        ));
        saveNewMessageToChatInDatabase(
                chatRepository.findByChatId(message.getChatId()).orElseThrow(() -> new RuntimeException("")),
                messageEntity
        );
    }

    public MessageEntity createNewMessage(String content, UserEntity author, List<UserEntity> receivers) {
        return new MessageEntity(
                content,
                LocalDate.now(),
                MessageType.TEXT,
                author.getUserId(),
                new ArrayList<>(receivers.stream().map(UserEntity::getUserId).collect(Collectors.toList()))
        );
    }

    private void saveNewMessageToChatInDatabase(ChatEntity chat, MessageEntity message) {
        chat.getMessageIds().add(message.getMessageId());
        chatRepository.save(chat);
    }

    public List<ChatDTO> getAllUserChats() {
        UserEntity user = userService.getCurrentUser()
                .orElseThrow(() -> new UserEntityNotFoundException("")); // TODO: Create chat exception

        List<ChatEntity> chats = getAllChatsByUserId(user.getUserId());
        return chats.stream().map(x -> new ChatDTO(
                        x.getChatId(),
                        x.getName().equals("") ?
                                x.getMembersIds().stream()
                                        .filter(y -> !y.equals(user.getUserId()))
                                        .findFirst()
                                        .map(userService::getUserById)
                                        .map(y -> y.get().getUsername())
                                        .get()
                                :
                                x.getName(),
                        x.getMembersIds().stream()
                                .map(userService::getUserById)
                                .map(y -> modelMapper.map(y.get(), UserDTO.class))
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    public List<ChatEntity> getAllChatsByUserId(String userId) {
        return chatRepository.findAllByMembersIdsContaining(userId);
    }

    public List<MessageDTO> getAllChatMessages(String chatId) {
        ChatEntity chat = chatRepository.findByChatId(chatId)
                .orElseThrow(() -> new RuntimeException("Could not find chat."));

        return chat.getMessageIds().stream()
                .map(x -> messageService.getMessageByMessageId(x).get())
                .map(x -> new MessageDTO(
                        chat.getChatId(),
                        userService.getUserByUsername(x.getSenderUsername()).get().getUsername(),
                        new ArrayList<>(x.getReceiversUsernames().stream().map(y -> userService.getUserByUsername(y).get().getUsername()).collect(Collectors.toList())),
                        x.getContent()
                        ))
                .collect(Collectors.toList());
    }
}
