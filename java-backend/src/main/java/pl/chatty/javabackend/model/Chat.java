package pl.chatty.javabackend.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Chat {

    private String id;
    private String name;
    private Timestamp createdTime;

    /* collections */
    private ArrayList<User> members;
    private ArrayList<Message> messages;

    public Chat() {}
    public Chat(String id, String name, Timestamp createdTime,
                ArrayList<User> members, ArrayList<Message> messages) {

        this.id = id;
        this.name = name;
        this.createdTime = createdTime;
        this.members = members;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
