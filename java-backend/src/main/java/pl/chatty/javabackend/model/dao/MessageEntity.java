package pl.chatty.javabackend.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.chatty.javabackend.model.MessageType;
import pl.chatty.javabackend.model.dto.User;

import java.sql.Timestamp;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageEntity {

    private String id;
    private String content;
    private Timestamp date;
    private User sender;
    private MessageType messageType;

    /* collections */
    private ArrayList<UserEntity> recipientList;

}
