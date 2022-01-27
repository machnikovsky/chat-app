package pl.chatty.javabackend.domains.chat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public ChatEntity(List<String> memberIds) {
        this.createdTime = LocalDate.now();
        this.membersIds = new ArrayList<>(memberIds);
        this.messageIds = new ArrayList<>();
    }

}
