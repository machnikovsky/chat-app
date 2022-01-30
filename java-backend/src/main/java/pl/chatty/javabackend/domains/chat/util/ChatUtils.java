package pl.chatty.javabackend.domains.chat.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.chatty.javabackend.domains.chat.model.dto.request.CreateChatRequestDTO;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatDTO;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatParticipantsDTO;
import pl.chatty.javabackend.domains.chat.model.entity.ChatEntity;
import pl.chatty.javabackend.domains.chat.repository.ChatRepository;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.message.model.entity.MessageEntity;
import pl.chatty.javabackend.domains.message.util.MessageUtils;
import pl.chatty.javabackend.domains.user.model.dto.response.UserDTO;
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
public class ChatUtils {

    private final ChatRepository chatRepository;
    private final UserUtils userUtils;
    private final ModelMapper modelMapper;


    @Async("asyncTaskExecutor")
    public CompletableFuture<List<MessageDTO>> getAllChatMessages(String chatId, MessageUtils messageUtils) {

        log.info("Started all chat messages using thread: {}", Thread.currentThread());
        long timeStart = System.currentTimeMillis();

        ChatEntity chat = chatRepository.findByChatId(chatId)
                .orElseThrow(() -> new RuntimeException("Could not find chat."));


        List<MessageDTO> messages = chat.getMessageIds().stream()
                .map(x -> messageUtils.getMessageByMessageId(x).get())
                .map(x -> new MessageDTO(
                        chat.getChatId(),
                        userUtils.getUserByUsername(x.getSenderUsername()).get().getUsername(),
                        new ArrayList<>(x.getReceiversUsernames().stream().map(y -> userUtils.getUserByUsername(y).get().getUsername()).collect(Collectors.toList())),
                        x.getMessageType(),
                        x.getContent(),
                        x.getImageContent()
                ))
                .collect(Collectors.toList());

        long timeEnd = System.currentTimeMillis();
        log.info("Finished getting all chat messages using thread: {}, took {} ms",
                Thread.currentThread(), timeEnd - timeStart);


        return CompletableFuture.completedFuture(messages);
    }

    @Async("asyncTaskExecutor")
    public CompletableFuture<List<ChatDTO>> getAllUserChats() {
        log.info("Getting all users chats using thread: {}", Thread.currentThread());
        UserEntity user = userUtils.getCurrentUser()
                .orElseThrow(UserEntityNotFoundException::new);

        List<ChatEntity> chats = getAllChatsByUserId(user.getUserId());
        return CompletableFuture.completedFuture(chats.stream().map(x -> new ChatDTO(
                        x.getChatId(),
                        ("".equals(x.getName()) || x.getName() == null) ?
                                x.getMembersIds().stream()
                                        .filter(y -> !y.equals(user.getUserId()))
                                        .findFirst()
                                        .map(userUtils::getUserById)
                                        .map(y -> y.get().getUsername())
                                        .get()
                                :
                                x.getName(),
                        x.getMembersIds().stream()
                                .map(userUtils::getUserById)
                                .map(y -> modelMapper.map(y.get(), UserDTO.class))
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList()));
    }

    public ChatEntity createNewChat(CreateChatRequestDTO createChatRequestDTO) {
        return addNewChatToDatabaseFromMessage(
                createChatRequestDTO
                        .getChatParticipantsUsernames()
                        .stream()
                        .map(username -> userUtils
                                .getUserByUsername(username)
                                .orElseThrow(() -> new UserEntityNotFoundException(username)))
                        .collect(Collectors.toList())
        );
    }

    public List<ChatEntity> getAllChatsByUserId(String userId) {
        return chatRepository.findAllByMembersIdsContaining(userId);
    }


    public ChatEntity addNewChatToDatabaseFromMessage(List<UserEntity> users) {
        ChatEntity chat = new ChatEntity();
        chat.setMembersIds(new ArrayList<>(users.stream().map(UserEntity::getUserId).collect(Collectors.toList())));
        chat.setCreatedTime(LocalDate.now());
        chat.setMessageIds(new ArrayList<>());
        chat.setName("");
        return chatRepository.save(chat);
    }

    public Optional<String> getChatIdByChatParticipants(ChatParticipantsDTO chatParticipantsDTO) {
        return getChatByChatParticipants(chatParticipantsDTO)
                .map(ChatEntity::getChatId);
    }

    public Optional<ChatEntity> getChatByChatParticipants(ChatParticipantsDTO chatParticipantsDTO) {
        return chatRepository
                .findByChatParticipants(chatParticipantsDTO.getChatParticipantsIds());
    }


    public void saveNewMessageToChatInDatabase(ChatEntity chat, MessageEntity message) {
        chat.getMessageIds().add(message.getMessageId());
        chatRepository.save(chat);
    }

    public Optional<ChatEntity> getChatByChatId(String chatId) {
        return chatRepository.findByChatId(chatId);
    }

    public ChatEntity saveChatInDatabase(ChatEntity chat) {
        return chatRepository.save(chat);
    }

    public ChatDTO mapChatToDTO(ChatEntity chatEntity) {
        return modelMapper.map(chatEntity, ChatDTO.class);
    }
}
