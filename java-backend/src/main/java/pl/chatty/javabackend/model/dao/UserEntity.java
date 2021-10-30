package pl.chatty.javabackend.model.dao;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import pl.chatty.javabackend.model.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserEntity {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;


    private Gender gender;
    private UsersRole usersRole;

    private ArrayList<UserEntity> friends;
    private ArrayList<UserEntity> blockedUsers;

}
