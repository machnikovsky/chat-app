package pl.chatty.javabackend.model.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.chatty.javabackend.model.Gender;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    /* enums */
    private Gender gender;

    /* collections */
    private ArrayList<User> friends;

}
