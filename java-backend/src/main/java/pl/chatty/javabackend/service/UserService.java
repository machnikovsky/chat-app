package pl.chatty.javabackend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.model.dto.response.UsersListDto;

import java.util.Optional;

public interface UserService {

    ResponseEntity<String> addUser(CreateUserRequest requestBody);

    ResponseEntity<String> removeUser(String userId);

    ResponseEntity<String> updateUser(String userId, CreateUserRequest requestBody);

    ResponseEntity<UserEntity> getUser(String userId);

    UsersListDto getUsers(Pageable paging);

    Optional<UserEntity> getUserByUsername(String username);

    Optional<UserEntity> getUserById(String userId);

    Optional<String> getCurrentUserUsername();

    Optional<UserEntity> getCurrentUser();
}
