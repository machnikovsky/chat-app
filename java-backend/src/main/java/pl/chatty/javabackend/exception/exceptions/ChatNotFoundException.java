package pl.chatty.javabackend.exception.exceptions;

import pl.chatty.javabackend.domains.chat.model.dto.response.ChatParticipantsDTO;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException() {
        super("Could not find any chats");
    }

    public ChatNotFoundException(ChatParticipantsDTO chatParticipantsDTO) {
        super("Could not find chat with participants: " + chatParticipantsDTO.getChatParticipantsIds());
    }

}
