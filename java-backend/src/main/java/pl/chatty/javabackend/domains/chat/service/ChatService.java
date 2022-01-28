package pl.chatty.javabackend.domains.chat.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.domains.chat.model.dto.request.CreateChatRequestDTO;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatDTO;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatParticipantsDTO;
import pl.chatty.javabackend.domains.chat.model.entity.ChatEntity;
import pl.chatty.javabackend.domains.chat.util.ChatUtils;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.message.util.MessageUtils;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.util.UserUtils;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatUtils chatUtils;
    private final MessageUtils messageUtils;
    private final UserUtils userUtils;

    public ResponseEntity<String> createChatAndGetChatId(CreateChatRequestDTO createChatRequestDTO) {
        return ResponseEntity.ok(chatUtils.createNewChat(createChatRequestDTO).getChatId());
    }

    public ResponseEntity<String> getChatByChatParticipants(ChatParticipantsDTO chatParticipantsDTO) {
        return chatUtils.getChatIdByChatParticipants(chatParticipantsDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserEntityNotFoundException("")); // TODO: Create chat exception
    }

    public ResponseEntity<List<ChatDTO>> getAllUserChats() {
        return ResponseEntity.ok(chatUtils.getAllUserChats());
    }

    public ResponseEntity<CompletableFuture<List<MessageDTO>>> getAllChatMessages(String chatId) {
        return ResponseEntity.ok(chatUtils.getAllChatMessages(chatId, messageUtils));
    }

    public ResponseEntity<ChatDTO> getExistingChatOrCreateNew(String userId) {
        UserEntity loggedInUser = userUtils.getCurrentUser()
                .orElseThrow(() -> new UserEntityNotFoundException("Current user"));

        List<String> memberIds = List.of(userId, loggedInUser.getUserId());

        ChatParticipantsDTO participants =
                new ChatParticipantsDTO(new ArrayList<>(memberIds));
        ChatEntity chat = chatUtils.getChatByChatParticipants(participants)
                .orElse(new ChatEntity(memberIds));

        ChatDTO chatDTO = chatUtils.mapChatToDTO(chatUtils.saveChatInDatabase(chat));
        return ResponseEntity.ok(chatDTO);
    }
}
