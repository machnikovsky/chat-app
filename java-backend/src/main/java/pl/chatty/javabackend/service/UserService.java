package pl.chatty.javabackend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.model.dto.response.UsersResponse;

public interface UserService {

    ResponseEntity<String> addUser(CreateUserRequest requestBody);

    ResponseEntity<String> removeUser(String userId);

    ResponseEntity<String> updateUser(String userId, CreateUserRequest requestBody);

    ResponseEntity<UserEntity> getUser(String userId);

    UsersResponse getUsers(Pageable paging);

}
