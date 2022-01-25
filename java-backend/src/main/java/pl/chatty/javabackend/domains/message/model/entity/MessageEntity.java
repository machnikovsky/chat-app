package pl.chatty.javabackend.domains.message.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;

@Document("messages")
@Data
@NoArgsConstructor
public class MessageEntity {

    @Id
    private String messageId;
    private String content;
    private LocalDate date;
    private MessageType messageType;
    private String senderUsername;
    private ArrayList<String> receiversUsernames;

    public MessageEntity(String content, LocalDate date, MessageType messageType,
                         String senderUsername, ArrayList<String> receiversUsernames) {
        this.content = content;
        this.date = date;
        this.messageType = messageType;
        this.senderUsername = senderUsername;
        this.receiversUsernames = receiversUsernames;
    }
}
