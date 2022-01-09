package pl.chatty.javabackend.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;

import java.util.ArrayList;

@Mapper
public interface UserMapper {


    default UserEntity mapToUserEntity(CreateUserRequest createUserRequest, PasswordEncoder passwordEncoder) {
        return new UserEntity(createUserRequest.getUsername(), createUserRequest.getFirstName(),
                createUserRequest.getLastName(), createUserRequest.getEmail(),
                passwordEncoder.encode(createUserRequest.getPassword()), createUserRequest.getPhoneNumber(),
                createUserRequest.getGender(), createUserRequest.getUsersRole(),
                new ArrayList<>(), new ArrayList<>()
        );
    }

}
