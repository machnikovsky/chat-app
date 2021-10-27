package pl.chatty.javabackend.service;

import org.springframework.stereotype.Service;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;

@Service
public class UserService {

    public void saveUser(CreateUserRequest userEntity) {
        UserEntity toSave = UserEntity.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phoneNumber(userEntity.getPhoneNumber())
                .gender(userEntity.getGender())
                .usersRole(userEntity.getUsersRole())
                .build();

        //abc.add(toSave) dodanie do bazy
    }

}
