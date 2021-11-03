package pl.chatty.javabackend.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatEntity {

    private String id;
    private String name;
    private Timestamp createdTime;

    private ArrayList<UserEntity> members;
    private ArrayList<MessageEntity> messages;

}
