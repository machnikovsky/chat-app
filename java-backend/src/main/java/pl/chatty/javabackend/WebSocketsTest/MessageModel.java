package pl.chatty.javabackend.WebSocketsTest;

public class MessageModel {

    private String messageContent;
    private String fromUser;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "messageContent='" + messageContent + '\'' +
                ", fromUser='" + fromUser + '\'' +
                '}';
    }
}
