package pl.chatty.javabackend.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.chatty.javabackend.model.MessageType;

import java.sql.Timestamp;
import java.util.ArrayList;

@Document("messages")
@Data
@NoArgsConstructor
public class MessageEntity {

    @Id
    private String messageId;
    private String content;
    private Timestamp date;
    private UserEntity sender;
    private MessageType messageType;

    private ArrayList<UserEntity> recipientList;

    public MessageEntity(String content, Timestamp date, UserEntity sender, MessageType messageType, ArrayList<UserEntity> recipientList) {
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.messageType = messageType;
        this.recipientList = recipientList;
    }
}
