package pl.chatty.javabackend.service.mapper;

import org.mapstruct.Mapper;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    default UserEntity mapToUserEntity(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity(createUserRequest.getUsername(), createUserRequest.getFirstName(),
                createUserRequest.getLastName(), createUserRequest.getEmail(), createUserRequest.getPassword(),
                createUserRequest.getPhoneNumber(), createUserRequest.getGender(), createUserRequest.getUsersRole(),
                new ArrayList<>(), new ArrayList<>()
        );
        return user;
    }

}
