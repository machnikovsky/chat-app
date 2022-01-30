package pl.chatty.javabackend.domains.user.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("users")
@Data
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Binary profileImage;

    private Gender gender;
    private UsersRole usersRole;

    private ArrayList<UserEntity> friends;
    private ArrayList<UserEntity> blockedUsers;

    public UserEntity(String username, String firstName, String lastName, String email, String password,
                      String phoneNumber,Binary profileImage, Gender gender, UsersRole usersRole, ArrayList<UserEntity> friends,
                      ArrayList<UserEntity> blockedUsers) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.gender = gender;
        this.usersRole = usersRole;
        this.friends = friends;
        this.blockedUsers = blockedUsers;
    }
}
