package pl.chatty.javabackend.domains.chat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;

@Document("chats")
@Data
@NoArgsConstructor
public class ChatEntity {

    @Id
    private String chatId;
    private String name;
    private LocalDate createdTime;

    private ArrayList<String> membersIds;
    private ArrayList<String> messageIds;

}
