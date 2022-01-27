package pl.chatty.javabackend.domains.user.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.domains.user.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.domains.user.model.dto.request.UpdatePasswordRequest;
import pl.chatty.javabackend.domains.user.model.dto.response.UsersListDto;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.repository.UserRepository;
import pl.chatty.javabackend.domains.user.util.UserUtils;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserUtils userUtils;


    public ResponseEntity<String> addUser(CreateUserRequest requestBody) {
        if (userUtils.addUser(requestBody)) {
            return new ResponseEntity<>("User successfully saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<String> removeUser(String userId) {
        if (userUtils.removeUser(userId)) {
            return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateUser(String userId, CreateUserRequest requestBody) {
        if(userUtils.updateUser(userId, requestBody)) {
            return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updatePassword(String userId, UpdatePasswordRequest requestBody) {
        if (userUtils.updateUserPassword(userId, requestBody)) {
            return new ResponseEntity<>("Password successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UsersListDto> getUsers(int page, int size) {
        if (page >= 0 && size > 0) {
            Pageable paging = PageRequest.of(page, size);
            return new ResponseEntity<>(userUtils.getUsers(paging), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UserEntity> getUser(String userId) {
        if (userRepository.existsById(userId)) {
            return new ResponseEntity<>(userRepository.getUserEntityByUserId(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public UserEntity getUserByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            return userRepository.getUserEntityByUsername(username);
        } else {
            throw new UserEntityNotFoundException("User not found");
        }
    }

    public ResponseEntity<UsersListDto> getUsersBesideSelf() {
        return ResponseEntity.ok(userUtils.getUsersBesideSelf());
    }
}
