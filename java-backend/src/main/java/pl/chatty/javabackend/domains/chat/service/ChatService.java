package pl.chatty.javabackend.domains.chat.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.domains.chat.model.dto.request.CreateChatRequestDTO;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatDTO;
import pl.chatty.javabackend.domains.chat.model.dto.response.ChatParticipantsDTO;
import pl.chatty.javabackend.domains.chat.util.ChatUtils;
import pl.chatty.javabackend.domains.message.model.dto.request.MessageDTO;
import pl.chatty.javabackend.domains.message.util.MessageUtils;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatUtils chatUtils;
    private final MessageUtils messageUtils;

    public ResponseEntity<String> createChatAndGetChatId(CreateChatRequestDTO createChatRequestDTO) {
        return ResponseEntity.ok(chatUtils.createNewChat(createChatRequestDTO).getChatId());
    }

    public ResponseEntity<String> getChatByChatParticipants(ChatParticipantsDTO chatParticipantsDTO) {
        return chatUtils.getChatByChatParticipants(chatParticipantsDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserEntityNotFoundException("")); // TODO: Create chat exception
    }

    public ResponseEntity<List<ChatDTO>> getAllUserChats() {
        return ResponseEntity.ok(chatUtils.getAllUserChats());
    }

    public ResponseEntity<List<MessageDTO>> getAllChatMessages(String chatId) {
        return ResponseEntity.ok(chatUtils.getAllChatMessages(chatId, messageUtils));
    }
}
