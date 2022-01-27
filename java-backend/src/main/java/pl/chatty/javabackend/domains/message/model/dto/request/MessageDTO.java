package pl.chatty.javabackend.domains.message.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageDTO {
    private String chatId;
    private String messageAuthorUsername;
    private ArrayList<String> messageReceiversUsernames;
}