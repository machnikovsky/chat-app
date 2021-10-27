package pl.chatty.javabackend.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.chatty.javabackend.model.dto.User;

import java.sql.Timestamp;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatEntity {

    private String id;
    private String name;
    private Timestamp createdTime;

    /* collections */
    private ArrayList<UserEntity> members;
    private ArrayList<MessageEntity> messages;

}
