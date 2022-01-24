package pl.chatty.javabackend.exception.exceptions;

public class UserEntityNotFoundException extends RuntimeException {
    public UserEntityNotFoundException(String username) {
        super("Could not find user with username: " + username);
    }
}
