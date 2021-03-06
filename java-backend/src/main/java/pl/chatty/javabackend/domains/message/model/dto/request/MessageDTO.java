package pl.chatty.javabackend.domains.message.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.Binary;
import pl.chatty.javabackend.domains.message.model.entity.MessageType;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MessageDTO {
    private String chatId;
    private String messageAuthorUsername;
    private ArrayList<String> messageReceiversUsernames;
    private MessageType messageType;
    private String messageContent;
    private String imageContent;

}
