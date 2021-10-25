package pl.chatty.javabackend.model;

import java.util.ArrayList;

public class User {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    /* enums */
    private Gender gender;
    private UsersRole usersRole;
    /* collections */
    private ArrayList<User> friends;
    private ArrayList<User> blockedUsers;


    public User() {}
    public User(String id, String username, String firstName, String lastName, String email, String password, String phoneNumber,
                Gender gender, UsersRole usersRole, ArrayList<User> friends, ArrayList<User> blockedUsers) {

        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.usersRole = usersRole;
        this.friends = friends;
        this.blockedUsers = blockedUsers;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public UsersRole getUsersRole() {
        return usersRole;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getBlockedUsers() {
        return blockedUsers;
    }
}
