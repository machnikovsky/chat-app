package pl.chatty.javabackend.domains.user.model.dto.request;

import lombok.Getter;
import org.bson.types.Binary;
import pl.chatty.javabackend.domains.user.model.entity.Gender;
import pl.chatty.javabackend.domains.user.model.entity.UsersRole;

@Getter
public class CreateUserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Binary profileImage;

    private Gender gender;
    private UsersRole usersRole;
}
