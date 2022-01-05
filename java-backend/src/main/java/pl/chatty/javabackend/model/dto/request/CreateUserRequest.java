package pl.chatty.javabackend.model.dto.request;

import lombok.Getter;
import pl.chatty.javabackend.model.Gender;
import pl.chatty.javabackend.model.UsersRole;

@Getter
public class CreateUserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    private Gender gender;
    private UsersRole usersRole;
}
