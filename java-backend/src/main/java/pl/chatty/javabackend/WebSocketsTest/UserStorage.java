package pl.chatty.javabackend.WebSocketsTest;

import java.util.HashSet;

public class UserStorage {

    public static HashSet<String> users;

    static {
        users = new HashSet<>();
        users.add("Anna");
        users.add("Lucyna");
        users.add("Irena");
    }

    public static void setUsers(String newUser){
        if(users.contains(newUser))
            throw new RuntimeException("Input user \"" + newUser + "is already in storage");
        users.add(newUser);
    }
}
