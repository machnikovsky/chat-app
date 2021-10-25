package pl.chatty.javabackend.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Message {

    private String id;
    private String content;
    private Timestamp date;
    private User sender;
    private MessageType messageType;

    /* collections */
    private ArrayList<User> recipientList;

    public Message() {}

    public Message(String id, String content, Timestamp date,
                   User sender, MessageType messageType, ArrayList<User> recipientList) {

        this.id = id;
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.messageType = messageType;
        this.recipientList = recipientList;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getDate() {
        return date;
    }

    public User getSender() {
        return sender;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public ArrayList<User> getRecipientList() {
        return recipientList;
    }
}
