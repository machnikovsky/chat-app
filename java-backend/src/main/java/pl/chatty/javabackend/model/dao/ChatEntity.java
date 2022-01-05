package pl.chatty.javabackend.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.ArrayList;

@Document("chats")
@Data
@NoArgsConstructor
public class ChatEntity {

    @Id
    private String chatId;
    private String name;
    private Timestamp createdTime;

    private ArrayList<UserEntity> members;
    private ArrayList<MessageEntity> messages;

}
