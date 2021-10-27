package pl.chatty.javabackend.model.dto;

public class UserLogin {

    private String id;
    private String username;
    private String email;
    private String password;


    public boolean validate() {
        return false;
    }
}
