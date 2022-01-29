package pl.chatty.javabackend.domains.user.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("users")
@Data
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
    private String profileImage;

    private Gender gender;
    private UsersRole usersRole;

    private ArrayList<UserEntity> friends;
    private ArrayList<UserEntity> blockedUsers;

    public UserEntity(String username, String firstName, String lastName, String email, String password,
                      String phoneNumber,String profileImage, Gender gender, UsersRole usersRole, ArrayList<UserEntity> friends,
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
